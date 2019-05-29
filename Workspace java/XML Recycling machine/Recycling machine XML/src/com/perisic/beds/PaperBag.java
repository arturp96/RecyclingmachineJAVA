package com.perisic.beds;

/**
 * This represents a paper bag. 
 * @author Marc Conrad
 *
 */
public class PaperBag extends DepositItem {
	//static int weight = 2; 
	static int size = 7; 
	/**
	 * 
	 */
	public PaperBag() { 
		value = 3; 
		weight = 3;
	}
	
	String getName() { 
		return "Paper Bag"; 
	}
}
