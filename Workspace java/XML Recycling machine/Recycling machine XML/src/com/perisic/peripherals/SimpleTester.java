package com.perisic.peripherals;
import com.perisic.beds.*;
/**
 * Tests the recycling machine.
 * @author Marc Conrad
 *
 */
public class SimpleTester {
	
	public static void main(String [] args) { 
		
		PrinterInterface myPrinter = new Display(); 
		
		CustomerPanel myPanel = new CustomerPanel(myPrinter); 
		myPanel.itemReceived(1);
		myPanel.itemReceived(1);
		myPanel.itemReceived(3);
		myPanel.itemReceived(2);
		myPanel.itemReceived(3);
		myPanel.itemReceived(4); // this is to test adding a paper bag into slot 4. 
		myPanel.itemReceived(4);
		myPanel.printReceipt();
		
		//RecyclingGUI myGUI = new RecyclingGUI(); 
		//myGUI.setVisible(true); 
	}
}
