package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
/**
 * Class of cubevolengine, inherits from computable.
 */
public  class CubeVolEngine implements Computable {

  
    private static final String engineName ="CUBEVOL";

   
    private double sideLength;

   
    private double volume;
    
    
    /**
     * A method that returns an enginename.
     */
    public static String getEngineName() {
        return engineName;
    }
 
    /**
     * This method gives the sidelength.
     */
    public double getSideLength() {
        return sideLength;}

    /**
     * This method sets the sideLength.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * This method returns a volume variable.
     */
    public double getVolume(){
        return volume;
    }

    /**
     * This method sets the volume variable.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This method is check inputs, to use try catch, Exception is least input vaule is 1, and Max input value num is 1
     */
    public void setInput(String[] inputs){
        try {
            if (inputs.length < 1)
                throw new MinimumInputNumberException("Exception-02: You need at least 1 input values for CUBEVOL.\r\n"	+ "");
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
    try{
    	if(inputs.length >=2)
    	throw new OneInputException("Exception-04: You need one input value for CUBEVOL.\r\n"	+ "");
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    sideLength = Double.parseDouble(inputs[0]);

    try {
    if(Double.parseDouble(inputs[0])<0)
     throw new NegativeNumberException("Exception-03: The input value cannot be negative for CUBEVOL.\r\n" + "");  
     }
    catch(NegativeNumberException e) {
    	System.out.println(e.getMessage());
		System.exit(0);
    }
}

    /**
     * This is how to calculate the CubeVol. Since volume is the cube of sideLength. This is expressed as expression.
     */
    public void compute(){
        volume = sideLength*sideLength*sideLength;
    }
    
    /**
     * This method returns a volume of type double.
     */
    public double getResult(){
        return volume;
    }
    
    


	}
 
