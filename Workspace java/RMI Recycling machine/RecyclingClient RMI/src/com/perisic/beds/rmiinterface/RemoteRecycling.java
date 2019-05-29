package com.perisic.beds.rmiinterface;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Vector;

public interface RemoteRecycling extends Remote {
	public int getNumberOfItems(String authenticationCookie) throws RemoteException;
	public String login(String user, String hash) throws RemoteException, ClassNotFoundException, SQLException;
	public void printReceipt() throws RemoteException; 
	public int removeItems()throws RemoteException;
	
}
