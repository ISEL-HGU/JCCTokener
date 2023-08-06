package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This is a class to find the square root.
 */
public class SQRTEngine implements Computable {


	private static String engineName ="SQRT";
    private double input;
    private double result;
    
    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args){
    	try {
            if(args.length>1){
                throw new OneInputException(getStringNames(), 0);
            }
            if(! args[0].matches("-?\\d+(\\.\\d+)?")) {
            	throw new MyNumberFormatException(getStringNames(), args[0]);
            }
            input = Double.parseDouble(args[0]);
            if(input<0){
            	throw new NegativeNumberException(getStringNames(), 0);
            }
    	}
    	catch (NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    }
    
    /**
     * This is the actual calculation method.
     */
    public void compute(){
        result = Math.sqrt(input);
    }
    
    /**
     * It returns enginame.
     */
    public String getStringNames(){
        return engineName;
    }
    
    /**
     * It returns input.
     */
    public double getInput() {
        return input;
    }
    
    /**
     * It sets input.
     */
    public void setInput(double input) {
        this.input = input;
    }
    /**
     * This is the method of obtaining the result value.
     */
    public double getResult() {
        return result;
    }
    /**
     * It sets result.
     */
    public void setResult(double result) {
        this.result = result;
    }
    /**
     * return engineName
     * 
     */
    public static String getEnginename() {
		return engineName;
	}
    /**
     * set enginename
     * @param enginename
     */
	
	public static void setEnginename(String enginename) {
		engineName = enginename;
	}
}
