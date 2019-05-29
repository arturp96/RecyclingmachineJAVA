package com.perisic.beds;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.perisic.util.RecyclingGUI;
/**
 * This is the interface of the system. It represents the interaction from 
 * the customer with the system. 
 * The customer panel knows the recycling machine (i.e. the Deposit Item Receiver class)
 * @author Marc Conrad
 *
 */
import com.perisic.util.SQL;
public class CustomerPanel {
	DepositItemReceiver receiver = null; 
	SQL sql = new SQL();
	
	
	public void itemReceived(int slot) { 
		receiver.classifyItem(slot); 
	}
	private String sessioncookie = "not set yet"; 
	
	
	public int Weight() {
		return receiver.calculateWeight();
	}
	public int getNumberOfItems(String authenticationCookie){
		if(authenticationCookie.equals(sessioncookie)){
			int result =  receiver.getNumberOfItems();
			return result;
		}else {
				return  -1;
		}
		
	}

		
	public CustomerPanel(PrinterInterface printer) {
			super(); 
			receiver = new DepositItemReceiver(printer); 
	}
	
	public String login(String login, String password) throws ClassNotFoundException, SQLException {
		ResultSet rs1 = sql.displayUser();
		String dbUser = rs1.getString("name");
		String dbPassword = rs1.getString("password");
		
		if(login.equals(dbUser)&& password.equals(dbPassword)) {
			sessioncookie = "Random" + Math.random();
			return sessioncookie;
		}else {
			return "wrong";
		}
	}
		
		public int removeItems() {
			
			int counter = receiver.removeItem();
			RecyclingGUI.progressBar.setValue(0);
			return counter;
			
		}
		
		
		
	public void printReceipt() { 
		receiver.printReceipt();
		
	}
	
}
