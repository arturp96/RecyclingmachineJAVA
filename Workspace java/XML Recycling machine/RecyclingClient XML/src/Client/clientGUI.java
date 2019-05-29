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


import org.apache.xmlrpc.XmlRpcClient;
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
	}


	private static final long serialVersionUID = 1L;
	SQL test  = new SQL();
	ResultSet rs ;
	
	
	private static String sessioncookie = "??";
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(items)){
			textArea.setText("Please login first!");
			try {
				   
				   XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); 
				   
				   Vector  args  = new Vector();
				   args.addElement(sessioncookie);
				   Object result = server.execute("sample.getNumberOfItems", args );
				   
				   if(result.toString().equals("-1")) {
					   System.out.println("Please log in first!");
					   textArea.setText("Please log in first! ");
				   } else {
					   System.out.println("There are" + " "+ result.toString() +" "+ "items in the machine!");
					   textArea.setText("There are" + " "+ result.toString() +" "+ "items in the machine!");
				   }
				   
				  } catch (Exception exception) {
				   System.err.println("JavaClient: " + exception);
 }
		
		
	}else if (e.getSource().equals(login)){
			
		JTextField usr = new JTextField();
		JPasswordField pwd = new JPasswordField();
			
		Object[] fields = {
				"Username", usr,
				"Password", pwd
				};
			
			
		    JOptionPane.showConfirmDialog(null, fields,"LOGIN PAGE",JOptionPane.OK_CANCEL_OPTION);
		    String pass = new String(pwd.getPassword());
		    String user = new String(usr.getText());
		    String hash =(getHash(pass.getBytes(),"SHA-1"));
			
		    
			
			textArea.setText("Login successful!");
			try {   

				XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); //
				Vector args = new Vector(); 
				args.addElement(user); 
				args.addElement(hash);
				Object result = server.execute("sample.login", args );

				if(result.equals("wrong")) { 
					System.out.println("Sorry, wrong login or password!"); 
					textArea.setText("Sorry, wrong login or password!");
				} else { 
					System.out.println("Login successful. Please continue"); 
					sessioncookie = result.toString(); 
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
			XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); //
			Vector args = new Vector(); 
			Object result = server.execute("sample.printReceipt", args );
			
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
		
			
			
		}else if(e.getSource().equals(reset)) {
			try {
				XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); //
				Vector args = new Vector(); 
				Object result = server.execute("sample.removeItems", args );
				
			} catch (Exception exception) {
				System.out.println("THere is nothing in the machine");
				System.err.println("JavaClient: " + exception);
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
		    	if(user.isEmpty() &&(pass.isEmpty())) {
		    		System.out.println("No inputs were entered, please try again");
		    		JOptionPane.showMessageDialog(null, "No inputs were entered, please try again");
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
	}
	public void print(String str) {
		System.out.println(str);
		textArea.setText(str);
		
		
	}
}