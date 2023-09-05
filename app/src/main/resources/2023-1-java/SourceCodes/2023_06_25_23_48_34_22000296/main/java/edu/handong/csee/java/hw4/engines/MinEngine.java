package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

//import java.util.Arrays;

public class MinEngine implements Computable {
	  private static final String engineName ="MIN";

	  double[] inputs;
	  double result;

	  public void setInput(String[] min){

	      inputs = new double[min.length - 1];
	      for(int i=1;i<min.length;i++){
	          
	          try {
	          	inputs[i-1]=Integer.parseInt(min[i]);
	              //System.out.println("" + number);
	          } catch (NumberFormatException e) {
	              //System.out.println("");
	        	  System.out.print(new MyNumberFormatException().getMessage(engineName));
	            	System.out.print("(" + min[i]);
	            	System.out.println(" is not a number value for " + engineName.toUpperCase() +".)");
	            	System.exit(0);
	          }
	      }
	      try {
	      	if (inputs.length<2)
	      		throw new MinimumInputNumberException();
	      }catch(MinimumInputNumberException e) {
	      	System.out.println(e.getMessage(engineName));
	          System.exit(0);
	      }

	  }

	  public void compute(){
	      double min=inputs[0];
	      for(int j=0; j<inputs.length;j++){
	          if(inputs[j]<min) min= inputs[j];
	      }
	      result = min;
	  }

	  public double getResult(){
	      return result;
	  }



	  
	}
