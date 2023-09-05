
package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This is a class to figure out LCM.
 */
public class LCMEngine implements Computable{

	private static String engineName ="LCM";
    private int[] a;
    private int result;
    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args){
    	try {
            a = new int[args.length];
            if (args.length <2){
                throw new MinimumInputNumberException(getEnginename(), 0);
            }
            for (int i=0; i<args.length; i++){
                if(! args[i].matches("-?\\d+(\\.\\d+)?")) {
                	throw new MyNumberFormatException(getEnginename(), args[i]);
                }
                a[i] = Integer.parseInt(args[i]);
            }
            for (int i =0; i< a.length; i++){
                if(a[i]<0){
                    throw new NegativeNumberException(getEnginename(), 0);
                }
            }
    	}
    	catch (MinimumInputNumberException e){
    		System.out.println(e.getMessage());
    		System.exit(result);
    	}
    	catch (NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(result);
    	}
    	catch (MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(result);
    	}
    }
    /**
     * This is the actual calculation method.
     */
    public void compute(){
        int lcm = a[0];
        for(int i =1; i<a.length; i++){
            lcm = (lcm * a[i]/getGCD(lcm, a[i]));
        }
        result = lcm;
    }
    /**
     * This is the method of obtaining the result value.
     */
    public double getResult(){
        return result;
    }
    /**
     * It is a method to find out two numbers of gcd.
     */
    public static int getGCD(int a, int b){
        if (b ==0){
            return a;
        }
        return getGCD(b, a % b);

    }
    /**
     * It returns enginame.
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * It returns a.
     */
    public int[] getA() {
        return a;
    }
    /**
     * This sets a.
     */
    public void setA(int[] a) {
        this.a = a;
    }
    /**
     * This sets result.
     */
    public void setResult(int result) {
        this.result = result;
    }
    /**
     * set engineName
     * @param enginename
     */
    public static void setEnginename(String enginename) {
		engineName = enginename;
	}
}
