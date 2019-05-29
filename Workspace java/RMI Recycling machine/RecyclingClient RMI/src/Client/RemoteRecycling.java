package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteRecycling extends Remote {
	public int getNumberOfItems(String authenticationCookie) throws RemoteException;
	public String login(String password) throws RemoteException; 
}
