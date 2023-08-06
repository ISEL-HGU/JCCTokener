package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
* The SQRTEngine class implements the Computable interface and provides methods to compute the square root of a given input value.
*/
public class SQRTEngine implements Computable {
    private static final String engineName = "SQRT";
    private double input;
    private double result;

    /**
     * Getter for the enginen name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }
    /**
     * Getter for the double array which stores the number for calculation of square root
     * @return the double type of the input
     */  
    public double getInput() {
        return input;
    }
    /**
     * Setter for the double array which stores the number for calculation of square root
     * @param inputs the double type of the number
     */
    public void setInput(double input) {
        this.input = input;
    }
    /**
     * Setter for the calculation of result
     * @param result
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
    * This method checks the input arguments for correctness and prints error messages if the input is invalid.
    * @param args The input arguments passed to the SQRTEngine instance.
     * @throws OneInputException 
     * @throws NegativeNumberException 
    */
    @Override
    public void checkInput(String[] args) throws OneInputException, NegativeNumberException{
        if (args.length > 2) throw new OneInputException("You need one input value for "+engineName+".");
        if (Integer.parseInt(args[1]) < 0) throw new NegativeNumberException("The input value cannot be negative for "+engineName+".");
    }

    /**
    * This method sets the input value for the computation.
    * @param args The input arguments passed to the SQRTEngine instance.
    */
    @Override
    public void setInput(String[] args){
        input = Double.parseDouble(args[1]);
    }

    /**
    *This method computes the square root of the input value.
    */
    @Override
    public void compute(){
        result = Math.sqrt(input);
    }

    /**
    * This method returns the result of the computation.
    * @return The square root of the input value.
    */
    @Override
    public double getResult(){
        return result;
    }
}
