package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;


public class Calculator {

    /**
    * main method
    * @param args
    */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
    * run method
    * @param args
    */
    public void run(String[] args) {

        String engineName = args[0].toUpperCase();

        Computable engine =null;
        InvalidCommandException invalid = null;

        switch(engineName) {
            case "LCM": // select LCM
                engine = new LCMEngine();
                break;
            case "GCD": // select GCD 
                engine = new GCDEngine();
                break;
            case "SQRT": // select SQRT
                engine = new SQRTEngine();
                break;
            case "FACTORIAL": // select FACTORIAL
                engine = new FactorialEngine();
                break;
            case "FIBONACCI": // select FIBONACCI
                engine = new FibonacciEngine();
                break;
            case "MAX": // select MAX
                engine = new MaxEngine();
                break;
            case "MIN": // select MIN
                engine = new MinEngine();
                break;
            case "CUBEVOL": // select CUBEVOL
                engine = new CubeVolEngine();
                break;
            case "SPHEREVOL": // select SPHEREVOL
                engine = new SphereVolEngine();
                break;
            default:
                invalid = new InvalidCommandException(); // if input is invalid
                
        }

        engine.setInput(args);
        engine.compute();

        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");

    }

}
