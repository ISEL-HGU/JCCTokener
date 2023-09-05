/**

This class represents a calculator program that takes a command and some values and performs a mathematical operation on the values using the selected command.
The program takes a command as the first argument and some values as the following arguments. It uses a switch statement to select an appropriate engine for the selected command.
The engine implements the Computable interface, which includes the setInput compute methods. The setInput method sets the input values, and the compute method performs the operation.
Once the computation is finished, the program displays the result on the console.
@author [Joonha Park]
@since [2023.04.28]
@version 3.0
*/
package edu.handong.csee.java.hw5;
import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.fileutil.FileManager;

import java.io.File;

import java.util.ArrayList;
public class Calculator {
    /**
 * The main method that creates a new instance of the Calculator class and calls the run method to start the program.
 * 
 * @param args An array of Strings that includes the command and some values to perform the operation.
 */
public static void main(String[] args) {
	OptionHandler optionhandler= new OptionHandler();
	Options options =  optionhandler.createOptions();
	if (args.length == 0 || args[0].isEmpty()) {
        optionhandler.printHelp(options);
        return;
    }
    Calculator myCalculator = new Calculator();
    myCalculator.run(args);
}

/**
 * The run method that takes an array of strings as input and performs a mathematical operation on the values using the selected command.
 * The method starts by converting the command to uppercase if args.lenght is not 0 and initialize a Computable engine object to null.
 * Then, a switch statement is used to select an appropriate engine for the selected command.
 * Once the engine is selected, the setInput method is called to set the input values, and the compute method is called to perform the operation.
 * Finally, the program displays the result on the console.
 * 
 * @param args An array of Strings that includes the command and some values to perform the operation.
 */
public void run(String[] args) {
	ArrayList<String> list;
	Computable engine =null;
	OptionHandler optionhandler= new OptionHandler();
	Options options =  optionhandler.createOptions();
	if(optionhandler.parseOptions(options, args)){// help 처리
		if (optionhandler.getHelpRequested()){
			optionhandler.printHelp(options);
			return;
		}
		if(optionhandler.getTask()!=null){ // task는 필수이기 때문에 없으면 help!
			//System.out.println(optionhandler.getInputValues());
			String Task = optionhandler.getTask().toUpperCase();
		if(optionhandler.getDataInputFilePath()!=null&&optionhandler.getInputValues()==null) {// 받을 파일이 있고 받을 값이 없으면..
			if(optionhandler.getDataOutputFilePath()!=null) {//내보낼 파일도 있으면
			if(Task.equals("SQRT")||Task.equals("MAX")||Task.equals("MIN")) {//가능한 기능들이  task로 설정되어 있으면
				File inputFile = new File(optionhandler.getDataInputFilePath());
				if(inputFile.isDirectory()) { // 디렉토리 라면...
			        File[] files = inputFile.listFiles();
			        ArrayList<Thread> threadsForFiles = new ArrayList<Thread>();
			        for (File file : files) {// 파일들 쭉 보기
			            if (file.isFile() && file.getName().endsWith(".csv")) {//cvs 파일이면
			            	CSVFileCalculator currentRunner = new CSVFileCalculator(file.getAbsolutePath(),Task,optionhandler.getDataOutputFilePath()); // 초기값 설정
			    			Thread thread = new Thread(currentRunner);//thread 생성
			    			thread.start();//해당 thread 시작
			    			threadsForFiles.add(thread);//thread 넣는 list
			    			//System.out.println("Thread started!");
			            }
			    		for (Thread thread : threadsForFiles) {//모든 thread가 끝날 때까지
			    		    try { 
			    		        thread.join(); // 각 스레드의 종료를 기다림
			    		    } catch (InterruptedException e) {
			    		    	//System.out.println("haha");
			    		        e.printStackTrace();
			    		    }
			    		}
			            }
			        System.exit(0);
				}else {
				list=FileManager.readLinesFromATxtFile(optionhandler.getDataInputFilePath());
				if(list==null) {// 받은 파일에 값이 없으면
					optionhandler.printHelp(options);
					return;
				}
				String[] stringArray = new String[list.size()];
				String[] rep=new String[2];
				ArrayList<String> numArrayList = new ArrayList<String>();
				String[] NumArray = new String[numArrayList.size()];
				list.toArray(stringArray);
				if(Task.equals("SQRT")) {
				rep[0]=Task;  
				for(int i=1;i<stringArray.length;i++) {// 하나씩 넣어서 SQRT 적용
					if(stringArray[i].matches("-?\\d+")!=true)// 정수가 아니면..
						continue;
					rep[1]=stringArray[i];
					engine= new SQRTEngine();
				    engine.setInput(rep);
				    engine.compute();
	                list.set(i,Double.toString(engine.getResult()));
	                }
				FileManager.writeAtxtFile(optionhandler.getDataOutputFilePath(),list);
				}else {
					// 그냥 받은 arraylist를 이중 arraylist를 만들어서 해결.. shit 
					//숫자만 있는 arraylist 만들었어.. 
					//그걸 각자 row 별로 나누어야됨.. 
					// 이중
					int count=0;// count=row
					for(String num:stringArray) {// 정수만 있는 numarray 만들기
						if(num.matches("-?\\d+")!=true)// 정수가 아니면..
							count++;
						else
							break;    
					}
					ArrayList<ArrayList<String>> csvData= new ArrayList<ArrayList<String>>();
			        for (int i = 0; i < list.size(); i += count) {
			            ArrayList<String> subArrayList = new ArrayList<>();
			            for (int j = i; j < i + count && j < list.size(); j++) {  
			                subArrayList.add(list.get(j));
			            }
			            csvData.add(subArrayList);
			        }
					csvData.get(0).add(Task);
					if(Task.equals("MAX")) 
					engine= new MaxEngine();
					else
					engine= new MinEngine();
					for(int i=1;i<csvData.size();i++) {
						csvData.get(i).add(0,Task);
						String[] num=new String[csvData.get(i).size()];
						csvData.get(i).toArray(num);
					    engine.setInput(num);
					    engine.compute();
					    csvData.get(i).remove(0);
					    int result=(int)(engine.getResult());
					    csvData.get(i).add(Integer.toString(result));
						}
					ArrayList<String> write=new ArrayList<String>();
					for(int i=0;i<csvData.size();i++) {
						for(int j=0;j<csvData.get(i).size();j++) {
							write.add(csvData.get(i).get(j));
						}
					}
					FileManager.writeAtxtFile(optionhandler.getDataOutputFilePath(),write);
				}
				System.exit(0);
				}
			
			}else {//input,ouput 다 있는데, SQRT 가 아니면..
				optionhandler.printHelp(options);
				return;
			}
		}else {// input 파일은 있는데, output 파일은 없는 경우
			optionhandler.printHelp(options);
			return;
		}
			}else if(optionhandler.getInputValues()!=null&&optionhandler.getDataInputFilePath()==null){// 받을 값이 있고, 데이타 파일이 없다면
			ArrayList<String> replace=new ArrayList<>();
			for(int i=0;i<args.length;i++) {
				if(args[i].equals("-t")||args[i].equals("--task")||args[i].equals("-v")||args[i].equals("--values")||args[i].equals(null)){// args[i]가 cli 명령어중 하나가 아니면
			}else {// 실행
				args[i]=args[i].replace("'","");
				if (args[i].contains(" ")) {
					String[] inputValuesArray = args[i].split("\\s+");
				    for(int j=0;j<inputValuesArray.length;j++) {
				     replace.add(inputValuesArray[j]);
				    }
				}else {
					replace.add(args[i]);
				}
			}
		}
			String[] replaceArray=new String[replace.size()];
			if (replace != null) {// 파일을 array에 
			    replace.toArray(replaceArray);
			    args=replaceArray;
			}
		}else {//받을 값 만 있거나 input 파일만 있는 상황이 아닌 경우. 
			optionhandler.printHelp(options);
			return;
		}
		}else {// task 가 없으면 help page
			optionhandler.printHelp(options);
			return;
		}
		String engineName = args[0].toUpperCase();
	for(int i=1; i<args.length; i++) {
        try {
        	int value = Integer.parseInt(args[i]);
        	
        }
        catch(NumberFormatException e) {
            try {
            	throw new MyNumberFormatException(engineName,args[i]);
            }catch(MyNumberFormatException a) {
            	System.out.println(a.getMessage ());
            	System.exit(0);
            	
            }
        }
    }
    try {
    switch(engineName) {
        case "LCM":
            engine = new LCMEngine();
            break;
        case "GCD":
            engine = new GCDEngine();
            break;
        case "SQRT":
            engine = new SQRTEngine();
            break;
        case "FACTORIAL":
            engine = new FactorialEngine();
            break;
        case "FIBONACCI":
            engine = new FibonacciEngine();
            break;
        case "MAX":
            engine = new MaxEngine();
            break;
        case "MIN":
            engine = new MinEngine();
            break;
        case "CUBEVOL":
            engine = new CubeVolEngine();
            break;
        case "SPHEREVOL":
            engine = new SphereVolEngine();
            break;
        default:
        	throw new InvalidCommandException(engineName);
    }
}catch(InvalidCommandException e) {
	System.out.println(e.getMessage ());
	System.exit(0);
}
    engine.setInput(args);
    engine.compute();
    System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
	}
}
 }