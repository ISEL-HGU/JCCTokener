package edu.handong.csee.java.hw4;

import edu.handong.csee.java.hw4.*;

/**
 * Calculator is the main class that runs the computation engines based on the input command.
 */
public class Calculator {
    /**
     * Main method of the Calculator program.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Calculator myCalculator = new Calculator();
        myCalculator.run(args);
    }

    /**
     * Runs the Calculator program with the given command-line arguments.
     * 
     * @param args The command-line arguments.
     */
    public void run(String[] args) {
        String engineName = args[0].toUpperCase();
        Computable engine = null;

        switch (engineName) {
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

        System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
    }
}
