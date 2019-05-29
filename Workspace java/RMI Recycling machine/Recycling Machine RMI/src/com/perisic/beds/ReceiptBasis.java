package com.perisic.beds;
import java.util.Vector; 

/**
 * This is where the data lives, i.e. cans, bottles and crates are recorded
 * in this class. We might call it our database (if we insist!). 
 * It also provides a summative statement about all the items inserted into the 
 * machine. 
 * @author Marc Conrad
 *
 */
public class ReceiptBasis {
	private Vector<DepositItem> myItems = new Vector<DepositItem>();
	/**
	 * @param item an item that has been inserted into the machine (such as can, bottle, crate). 
	 */
	public void addItem(DepositItem item) { 
		myItems.add(item); 
		item.number = myItems.indexOf(item)+1; 
		
	}
	public void removeItem(DepositItem item) {
		myItems.removeAllElements();
	}
	/**
	 * Calculates a summary based on the items inserted.
	 * @return the overall value of the items inserted by the customer.
	 */
	
	public int calcWeight() {
		int totalWeight= 0;
		for(int i=0; i < myItems.size();i++) {
			DepositItem item = myItems.get(i);
			totalWeight +=item.weight;
		}
		return totalWeight;
	} 
	
	public String computeSum() { 
		String receipt = ""; 	
		int sum = 0; 
		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i); 
			receipt = receipt + item.number +". "+item.getName()+" (" +item.value + ")"; 
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value; 
			//System.out.println("\n");
		}

		receipt = receipt +"______________" +"\n Total: "+sum; 
		
		if(sum>=1){
			myItems.removeAllElements();
			return receipt; 
		}
		else{//this statement makes sure that if the machine has been emptied the receipt isnt printed, instead we have an error message
			System.out.println("Please add something to\n the machine before\n printing the receipt");
			return "Please add something to\n the machine before\n printing the receipt";
		}
	}

		
		
		
	}
	
	
	/*public String resetSum() {
		String receipt = "";
		int sum = 0;
		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i); 
			receipt = receipt + item.number +". "+item.getName()+" (" +item.value + ")"; 
			receipt = receipt + System.getProperty("line.separator");
			sum = 0;
		
		
		}
		receipt = receipt +"______________" +"\n Total: "+sum; 
		return receipt;
	}
	*/

