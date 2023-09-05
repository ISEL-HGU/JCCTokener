package edu.handong.csee.java.hw5;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.clioptions.*; 

import java.util.Arrays;

import org.apache.commons.cli.Options;


/**
* Execute the main method with run method and print the result of calculation
*
* @version 1.5
* @since 1.0
*/
public class Calculator {

    /**
    * Create the calculator class to execute the calculation
    * @param  args The command line arguments provided by the user. The first argument should be the name of the computation engine to be used.
    */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
     * Takes the command line arguments and runs a computation engine to perform a specific calculation. It first checks if there are any inputs, then determines which computation engine to use based on the first argument. It then checks the validation of input and performs the computation. Finally, prints the result.
     * @param args The command line arguments provided by the user. The first argument should be the name of the computation engine to be used.   
     */
    public void run(String[] args) {
    	OptionHandler optionHandler = new OptionHandler();
    	Options options = new Options();
    	
    	options = optionHandler.createOptions();
    	if(!optionHandler.parseOptions(options, args)) {
    		// false 로 나오면 각각에 대하여 에러처리 
    	}
    	
    	
    	try {
    		if(args.length == 0) throw new InvalidCommandException();    		
    	}catch(InvalidCommandException ice) {
    		optionHandler.printHelp(null);
//    		System.out.print("Exception-01: ");
//    		System.out.println("Invalid command: ");
//    		System.out.println(ice.getMessage());
    		System.exit(0);
    	}
    	
    
    	
    	// args[1] 부터 optionHandler.inputValues를 array에 넣어줘야함 
        // String[] inputValues = StringUtils.split(optionHandler.getInputValues()); 
        
        
        try {
        		// Split the input by irregular spaces
        		String[] inputValues = optionHandler.getInputValues().split("\\s+");		// possible exception 1
        		// args[1] 이후로 inputValue를 다시 넣어줌 
        		int argsIndex=1;
        		String[] newArgs = new String[inputValues.length+1];
        		args = newArgs;
        		
        		
        		for (String value : inputValues) {
        			//System.out.println("=> Input values: "+value);
        			args[argsIndex]=value;
        			argsIndex++;
        		} 
        		
        		args[0] = optionHandler.getTask();		// possible exception 2
        		
        		// TEST
        		/*
        		System.out.println("=> args.length: "+args.length);
        		for (int i=0; i<args.length; i++) {
        			System.out.println("=> args["+i+"]: "+args[i]);
        		} */
        			
        }catch(NullPointerException npe) {
        	optionHandler.printHelp(null);
        	System.exit(0);
        }
        
        String engineName = args[0].toUpperCase();
        Computable engine = null;
        
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
                try {
                	throw new InvalidCommandException("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100\n");
            	
                }catch(InvalidCommandException ice) {
                	System.out.print("Exception-01: ");
                	System.out.println("Invalid command: " + engineName);
                	System.out.println(ice.getMessage());
                	System.exit(0);
                }
        }
        
        
        try {
        	engine.checkInput(args);        	
        }catch(MinimumInputNumberException mie) {
        	System.out.print("Exception-02: ");
        	System.out.println(mie.getMessage());
        	System.exit(0);  
        }catch(NegativeNumberException nne) {
        	System.out.print("Exception-03: ");
        	System.out.println(nne.getMessage());
        	System.exit(0);  
        }catch(OneInputException oie) {
        	System.out.print("Exception-04: ");
        	System.out.println(oie.getMessage());
        	System.exit(0);  
        }catch(MyNumberFormatException mnfe) {
        	System.out.print("Exception-05: ");
        	System.out.println(mnfe.getMessage());
        	System.exit(0);
        }
        
        engine.setInput(args);
        engine.compute();

        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");

    }
}