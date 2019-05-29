package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

import com.perisic.beds.rmiinterface.RemoteRecycling;

import java.rmi.Naming;


import Client.SQL;


public class clientGUI extends JFrame implements ActionListener  {
	
	
	
	public static  String getHash(byte[] inputBytes, String algorithm) {
		String hashValue = "";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(inputBytes);
			byte[] db = md.digest();
			hashValue = DatatypeConverter.printHexBinary(db).toLowerCase();
			
		}catch(Exception e) {
			
		}
		return hashValue;
	}// the code above is the hashing algorithm used for the password hashing


	private static final long serialVersionUID = 1L;
	SQL test  = new SQL();// creating a new instance of the sql class
	ResultSet rs ;// initializing the resultset from the sql class
	
	
	private static String sessioncookie;//initlizing the session cookie from the login method inside the customer panel  //= "??";
	private RemoteRecycling myRecyclingMachine = null; 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(items)){
			textArea.setText("Please login first!");
			try {
				   
				
				int result = myRecyclingMachine.getNumberOfItems(sessioncookie); 
				if( result == -1) { 
					System.out.println("Please login first"); //this method only show the number of items once the user sucessfully logs in
															// otherwise it tells the user to try again
					
				} else { 
					System.out.println("There are "+result+" items in the machine.");
					textArea.setText("There are "+result+ " items in the machine");//this prints the number of items on the display and console
				}

			} catch (Exception exception) {
				System.err.println("JavaClient: " + exception);
			}
		}else if (e.getSource().equals(login)){
			
			
			
			JTextField usr = new JTextField();// creating a jtext field for the username
			JPasswordField pwd = new JPasswordField();//creating the Jpassword field for the password
			
			Object[] fields = {
					"Username", usr,
					"Password", pwd
				};//putting the two fields in an array 
			
			
			
			
			
		    JOptionPane.showConfirmDialog(null, fields,"LOGIN PAGE",JOptionPane.OK_CANCEL_OPTION);//creating a confirmation dialog in a j option pane for the two fields.
		    String pass = new String(pwd.getPassword());//converting the variable pwd into a string
		    String user = new String(usr.getText());//converting the variable usr into a string
		    String hash =(getHash(pass.getBytes(),"SHA-1"));// converting the string pass into hashed chracters using the SHA -1 algorithm
			
		    
			
			//textArea.setText("Login successful!");
			try {   

				

				String result = myRecyclingMachine.login(user,hash); 
				if(result.equals("wrong")) { 
					System.out.println("Sorry, wrong login or password!"); 
					textArea.setText("Sorry, wrong login or password!");//if the user name and password dont match a user in a database, it will tell you to try again
				} else { 
					System.out.println("Login successful. Please continue"); 
					textArea.setText("Login successful!");
					sessioncookie = result.toString(); // if the user types the correct combination it will return the session cookie and give them access
					
				}
				

			} catch (Exception exception) {
				System.err.println("JavaClient: " + exception);
			}
			
			
			
				
		}else if( e.getSource().equals(logout)) { 
			sessioncookie = "Reset"+Math.random(); 
			System.out.println("Logout. Have a good time!"); 
			textArea.setText("Logout. Have a good time!");
		
		} else if( e.getSource().equals(receipt)) {
		
		try {
			
			Vector args = new Vector(); 
			myRecyclingMachine.printReceipt(); 
			
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
		
			
			
		}else if(e.getSource().equals(reset)) {
			try {
				
				Vector args = new Vector(); 
				int result = myRecyclingMachine.removeItems(); 
				
			} catch (Exception exception) {
				System.out.println("THere is nothing in the machine");
				
			}
			
			
		}else if (e.getSource().equals(slot7)) {
			try {
				rs =  test.displayData();
				
				while(rs.next()) {
					System.out.println(rs.getString("name") +" " + rs.getString("date"));
					textArea.append(rs.getString("name") + " " + rs.getString("date"));
				}
				
			} catch (ClassNotFoundException | SQLException exception) {
				
				exception.printStackTrace();
				System.out.println("there is an error");
			} 
			
		} else if (e.getSource().equals(register)) {
			
			
			JTextField usr = new JTextField();
			JPasswordField pwd = new JPasswordField();
			

			Object[] fields = {
					"Username", usr,
					"Password", pwd
				};
		    
			JOptionPane.showConfirmDialog(null, fields,"REGISTER PAGE",JOptionPane.OK_CANCEL_OPTION);
		    String pass = new String(pwd.getPassword());
		    String user = new String(usr.getText());
		    String hash =(getHash(pass.getBytes(),"SHA-1"));
		  
		    
		    try {
		    	if(user.isEmpty() && (pass.isEmpty())) {// this makes sure that a user can't be created without typing anything into the fields.
		    		System.out.println("no inputs please try again");
		    		JOptionPane.showMessageDialog(null, "No imputs please try again");
//		    		clientGUI myGUI = new clientGUI();
//		    		myGUI.setVisible(true);
		    	}else {
		    		test.addUsers(user,hash); 
		    	}
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		    
		   
		}
		
		
			
	} 
	
	
	
	
	
	JButton items = new JButton("Items"); 
	JButton login = new JButton("Log In"); 
	JButton logout = new JButton("Log Out"); 
	JButton reset = new JButton("Reset");
	JButton slot7 = new JButton("SQL Data");
	JButton register = new JButton("Register");
	
	JButton receipt = new JButton("Reciept"); 
	
	
	JPanel panel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	JTextArea textArea = new JTextArea();
	
	
	public clientGUI() {
		super();
		setTitle("Remote Controlled Client");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		 
		panel.add(items);
		items.setBounds(10,11,119,23);
		items.setBackground(Color.CYAN);
		items.addActionListener(this);
		
		
		panel.add(login);
		login.setBounds(165, 263,119 ,23 );
		login.setBackground(Color.CYAN);
		login.addActionListener(this);	
		
		panel.add(logout); 
		logout.setBounds(10,145,119,23);
		logout.setBackground(Color.CYAN);
		logout.addActionListener(this);
		
		panel.add(register);
		register.setBounds(346,263,119,23);
		register.setBackground(Color.CYAN);
		register.addActionListener(this);
		
		
		
		panel.add(receipt); 
		receipt.setBounds(10, 77, 119, 23);
		receipt.setBackground(Color.CYAN);
		receipt.addActionListener(this);
		
		
		panel.setLayout(null);
		
		scrollPane.setBounds(165, 11, 300, 225);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);
		reset.setBackground(Color.CYAN);
		
		
		reset.setBounds(10, 111, 119, 23);
		panel.add(reset);
		receipt.setBackground(Color.CYAN);
		reset.addActionListener(this);
		
		slot7.setBounds(10,43,119,23);
		
		slot7.setBackground(Color.CYAN);
		slot7.addActionListener(this);
		panel.add(slot7);
		
		
		
		panel.repaint();
	
	}
	
	
	
	
	public static void main(String [] args ) { 
		clientGUI myGUI = new clientGUI(); 
		myGUI.setVisible(true);
		try {
			   
			  myGUI.myRecyclingMachine
					= (RemoteRecycling) Naming.lookup("rmi://localhost/RecyclingService1718"); 
			   
			  } catch (Exception exception) {
				  exception.printStackTrace();
			   System.err.println("JavaClient: " + exception);
			   }
	}
	public void print(String str) {
		System.out.println(str);
		textArea.setText(str);
		
		
	}
}