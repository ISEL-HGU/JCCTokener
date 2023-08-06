package edu.handong.csee.java.hw4;

import edu.handong.csee.java.hw4.Computable;
import edu.handong.csee.java.hw4.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.exceptions.NegativeNumberException;

/**
 * MaxEngine class calculates the maximum value among the input values.
 */
public class MaxEngine implements Computable {
    private static final String engineName = "MAX";
    double inputs[];
    double result;
    int count = 0;

    /**
     * Sets the input values for the MAX calculation.
     * 
     * @param args The array of input values.
     */
    @Override
    public void setInput(String[] args) {
    	boolean isFirstElementNotDouble;
    	try {
        for (int i = 0; args[i] != null; i++) {
        	if(TypeChecker.checkType(args[i]) == "double") {
        		inputs[i] = Double.parseDouble(args[i + 1]);
        		count++;
        	}
        }
        } catch (NumberFormatException e) {
    	    isFirstElementNotDouble = true;
    	    throw new MyNumberFormatException(args);
    	}
         
    } 	
	/**
     * Computes the maximum value among the input values.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length >= 2) {
                throw new MinimumInputNumberException("MAX.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("MAX.");
            } else {
                double max = inputs[0];
                for (int i = 1; i <= count; i++) {
                    if (max < inputs[i]) {
                        max = inputs[i];
                    }
                }
                result = max;
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the computed result, which is the maximum value.
     * 
     * @return The maximum value result.
     */
    @Override
    public double getResult() {
        return result;
    }
    public class TypeChecker {
        public static String checkType(Object args) {
            if (args instanceof String) {
                return "string";
            } else if (args instanceof Double || args instanceof Integer || args instanceof Float) {
                return "double";
            } else {
                return "unknown";
            }
        }
    }
}
