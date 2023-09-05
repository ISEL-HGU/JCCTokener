package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;

/**
* This class is computing LCMEngine and implement Computable(interface) 
*/
public class LCMEngine implements Computable{
    private static final String engineName ="LCM"; // 최소공배수
    private int[] a;
    private int result;
    /**
     * This method is setting input.
     * @param args
     */
    public void setInput(String[] args) {
    	OptionHandler optionhandler = new OptionHandler();
    		optionhandler.run(args); 
    		String inputvalues = optionhandler.getInputValues(); 
    	try {
    		String[] values = inputvalues.split(" ");
    		a = new int[values.length];
	    	if(a.length < 2) {
	    		throw new MinimumInputNumberException(engineName);
	        }
	        int i;
	        for(i=0; i<a.length; i++){
	            if(Integer.parseInt(values[i]) < 0){
	            	throw new NegativeNumberException(engineName);
	            }
	            
	            // 발생된 예외가 없을 시 실행되는 문장
	            a[i] = Integer.parseInt(values[i]);
	            //System.out.println(a[0] + " " + a[1]);
	        }
    	}
    	catch(MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.out.println("(" + inputvalues + " is not a number value for "+ engineName + ".)");
    		System.exit(0);
    	}
    	catch(NumberFormatException e) {
    		throw new MyNumberFormatException();
    	}
    }
    
    /**
     * This is gcd method.
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
    	int lcm = this.a[0];
    	//System.out.println(a.length);
        for(int i=1; i<a.length; i++){
            int product = lcm * a[i];
            int tempLcm = lcm;
            //this.result = lcm;
            while(a[i] != 0) {
                int temp = a[i];
                a[i] = lcm % a[i];
                lcm = temp;
            }
            lcm = product / lcm;
            lcm = lcm * tempLcm / gcd(lcm, tempLcm);
        }
        this.result = lcm;
    }
 
    /**
     * This method return this.a[](array)
     * @return
     */
    public int[] getArr() {
    	return this.a;
    }
    /**
     * This method gives result.
     * @return
     */
    public double getResult() {
        return this.result;
    }
    /**
     * This method is return this.engineName.
     * @return
     */
    public String getEngineName() {
        return LCMEngine.engineName;
    }
}
