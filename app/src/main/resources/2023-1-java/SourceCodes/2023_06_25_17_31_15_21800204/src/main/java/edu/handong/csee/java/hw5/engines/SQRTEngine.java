package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import java.util.ArrayList;

public class SQRTEngine implements Computable {
    private static final String engineName = "SQRT";
    private double input;
    private double result;

    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException {
        this.result = Math.sqrt(input);
    }

    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException {
        for (String line : args) {
            try {
                double input = Double.parseDouble(line);
                if (input < 0) {
                    throw new NegativeNumberException(engineName);
                } else {
                    this.input = input;
                    break;  // only the first valid input is taken
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(engineName, line);
            }
        }
    }


    public double getResult() {
        return this.result;
    }

    public double getInput() {
        return this.input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
