package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.clioptions.*;

import java.io.FileNotFoundException;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * the class about function that is calculating Cube volume, Factorial, Fibonacci, Greatest common divisor, Least common multiple, Sphere volume, Square root, Maximum value, Minimum value.
 * @author kim hongchan
 *
 */
public class Calculator {

	/**
	 * for Calculator starting, get parameter from standard input.
	 * @param args
	 */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }
    
    /**
     * The method that actually calls the compute function class to make it work.
     * @param args
     */
    public void run(String[] args) {
    	OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	
    	try {
    		
    	optionHandler.parseOptions(options, args);
       
    	String engineName = optionHandler.getTask();
    	
        Computable engine =null;

        
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
         
        if(options.hasOption("i"))
        	((SQRTEngine)engine).computeNwriteOnFile(optionHandler.getDataInputFilePath(), optionHandler.getDataOutputFilePath());
        else{
        	engine.setInput(optionHandler.getInputValues());
        	engine.compute();
        }
        
        if(optionHandler.getHelpReqeusted()) optionHandler.printHelp(options);
           
        if(optionHandler.getDataInputFilePath() == null && optionHandler.getDataOutputFilePath() == null )
        	System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
        
    	  }catch(OneInputException e) {
          	System.out.println(e.getMessage());
          	System.exit(0);
          }
          catch(InvalidCommandException e) {
          	System.out.println(e.getMessage());
          	//optionHandler.printHelp(options);
          	System.exit(0);
          }
          catch(MinimumInputNumberException e) {
          	System.out.println(e.getMessage());
          	System.exit(0);
          }
          catch(NegativeNumberException e) {
          	System.out.println(e.getMessage());
          	System.exit(0);
          }
          catch(NumberFormatException e) {
          	String error = e.getMessage().substring(e.getMessage().indexOf("\"")+1,e.getMessage().lastIndexOf("\""));
          	e = new MyNumberFormatException(error,optionHandler.getTask());
          	System.out.println(e.getMessage());
          	System.exit(0);
          }
          catch(InvalidOptionException e) {
          	optionHandler.printHelp(options);
          	System.exit(0);
          }catch(FileNotFoundException e) {
          	optionHandler.printHelp(options);
          	System.exit(0);
          }catch(NullPointerException e) {
          	optionHandler.printHelp(options);
          	System.exit(0);
          } catch (ParseException e) {
        	optionHandler.printHelp(options);
        	System.exit(0);
		}  

    }


}