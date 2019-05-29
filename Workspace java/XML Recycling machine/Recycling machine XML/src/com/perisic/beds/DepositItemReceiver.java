package com.perisic.beds;

/** 
 * This class represents the overall system. 
 * @author Marc Conrad
 *
 */
public class DepositItemReceiver extends ReceiptBasis{
	ReceiptBasis theReceiptBasis = null; 
	
	DepositItem item = null;
	
	PrinterInterface printer = null; 
	int counter =0 ;
	
	
	public void createReceiptBasis() { 
		theReceiptBasis = new ReceiptBasis(); 
	}

	public int calculateWeight() {
		int weight = 0;
		weight = theReceiptBasis.calcWeight();	
		return weight;
	}
	public int getNumberOfItems(){
		return counter;
		
	}
//	public int counterReset() {
//		counter = 0;
//		return counter;
//	}
//	
	public void classifyItem(int slot) { 
		
		DepositItem item = null; 
		
		if( slot == 1 ) { 
			item = new Can(); 
		} else if( slot == 2 ) { 
			item = new Bottle(); 
		} else if ( slot == 3 ) { 
			item = new Crate(); 
		} else if (slot == 4) { 
			item = new PaperBag(); 	
		} else if (slot ==5) {
			item = new PlasticBottle();
		}


		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		theReceiptBasis.addItem(item); 
		counter += 1;
	}
	/**
	 * 
	 */

	
	public DepositItemReceiver(PrinterInterface printer) {
		super();
		this.printer = printer;
	}
	
	public void printReceipt() { 
		String str = theReceiptBasis.computeSum();;
		
			
			printer.print(str);
			removeItem();
			counter =0;
			//theReceiptBasis.removeItem(item);
			theReceiptBasis = null;
		
		
		//this try and catch block displays an error message every time the receipt button is pressed without items being inserted first
		
		 
		//theReceiptBasis = null; 
		
	}
	int removeItem() {
		theReceiptBasis.removeItem(item);
		counter = 0;
		return counter;
	}
	/*public void resetReceipt() {
		String str = "";
		try {
			str = theReceiptBasis.resetSum();
		}catch (Exception e) {
			str =  "not items were added!";
		}
		printer.print(str); 
		theReceiptBasis = null; 
	}*/
}
