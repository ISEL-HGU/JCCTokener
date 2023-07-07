package edu.handong.csee.java.hw3;

import edu.handong.csee.java.hw3.engines.*;

/**
 * This is the main class of this program
 */
public class Calculator {
    /**
     * This is the main method
     * @param args
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
     * This is the run method
     * @param args
     */
    public void run(String[] args) {
        if(args.length == 0) {
            System.out.println("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL.");
            System.out.println("For example, java -cp [your classpath] edu.handong.csee.java.hw2.Calculator MAX 12 4 5 45 100");
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
                System.out.println("Invalid command: " + engineName);
                System.exit(0);
        }

        engine.setInput(args);
        engine.compute();

        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");

    }


}