package com.perisic.peripherals;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import com.perisic.beds.CustomerPanel;
import com.perisic.beds.rmiinterface.RemoteRecycling;

public class RecyclingMachineRMIImplementation extends UnicastRemoteObject implements RemoteRecycling {

	public RecyclingMachineRMIImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	CustomerPanel myCustomerPanel = null; 
	
	public void setCustomerPanel(CustomerPanel myPanel) { 
		myCustomerPanel = myPanel; 
	}

	@Override
	public int getNumberOfItems(String authenticationCookie) throws RemoteException {
		// TODO Auto-generated method stub
		// Needs Implementation!!!! '5' is only for testing purposes. 
		return myCustomerPanel.getNumberOfItems(authenticationCookie); 
	}

		@Override
	public String login(String login, String password) throws RemoteException, ClassNotFoundException, SQLException {
		return myCustomerPanel.login(login, password); 
	}

		@Override
		public void printReceipt() {
			// TODO Auto-generated method stub
			myCustomerPanel.printReceipt();
		}

		@Override
		public int removeItems() {
			return myCustomerPanel.removeItems();
		}

}
