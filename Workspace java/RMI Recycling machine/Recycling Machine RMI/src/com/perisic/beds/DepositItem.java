package com.perisic.beds;

/**
 * This represents an item that has been inserted into 
 * the recycling machine. 
 * @author Marc Conrad
 *
 */
public abstract class DepositItem {

	int number; 

	int value; 
	
	int weight;
	String getName() { 
		return this.getClass().getSimpleName();   // needs something better. 
	}
}
