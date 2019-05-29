package com.perisic.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.perisic.beds.CustomerPanel;
import com.perisic.beds.PrinterInterface;
import com.perisic.peripherals.Display;

import com.perisic.util.SQL;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout.Alignment;

import org.apache.xmlrpc.WebServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
/**
 * A Simple Graphical User Interface for the Recycling Machine.
 * @author Marc Conrad
 *
 */
public class RecyclingGUI extends JFrame implements ActionListener, PrinterInterface  {

	private static final long serialVersionUID = 1L;
	
	
	
	CustomerPanel myPanel = new CustomerPanel(this); 
	SQL test  = new SQL();
	ResultSet rs;

	
	public void actionPerformed(ActionEvent e) {
		
				
		
		
		if(e.getSource().equals(slot1)){
			myPanel.itemReceived(1);
			System.out.println("Can Insterted!");
			textArea.setText("Can Inserted!");
			try {
				test.addData("Can");
				
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(myPanel.Weight()<=100) {
				progressBar.setValue((int)myPanel.Weight());
			}else {
				progressBar.setValue(100);
				slot1.setVisible(false);
				slot2.setVisible(false);
				slot3.setVisible(false);
				slot4.setVisible(false);
				slot5.setVisible(false);
				textArea.setText("machine full");
				
				
			}							
		}

		else if (e.getSource().equals(slot2)){
			myPanel.itemReceived(2);
			System.out.println("Bottle Insterted!");
			textArea.setText("Bottle Inserted!");
			try {
				test.addData("Bottle");
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(myPanel.Weight()<=100) {
				progressBar.setValue((int)myPanel.Weight());
			}else {
				progressBar.setValue(100);
				slot1.setVisible(false);
				slot2.setVisible(false);
				slot3.setVisible(false);
				slot4.setVisible(false);
				slot5.setVisible(false);
				textArea.setText("machine full");
				
				
			}
		}
		
		else if (e.getSource().equals(slot3)){
			myPanel.itemReceived(3);
			System.out.println("Crate Insterted!");
			textArea.setText("Crate Inserted!");
			try {
				test.addData("Crate");
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(myPanel.Weight()<=100) {
				progressBar.setValue((int)myPanel.Weight());
			}else {
				progressBar.setValue(100);
				slot1.setVisible(false);
				slot2.setVisible(false);
				slot3.setVisible(false);
				slot4.setVisible(false);
				slot5.setVisible(false);
				textArea.setText("machine full");
				
			}	
		}
		else if (e.getSource().equals(slot4)){
			myPanel.itemReceived(4);
			System.out.println("Paper Bag Insterted!");
			textArea.setText("Paper Bag Inserted!");
			try {
				test.addData("PaperBag");
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(myPanel.Weight()<=100) {
				progressBar.setValue((int)myPanel.Weight());
			}else {
				progressBar.setValue(100);
				slot1.setVisible(false);
				slot2.setVisible(false);
				slot3.setVisible(false);
				slot4.setVisible(false);
				slot5.setVisible(false);
				textArea.setText("machine full");
			
				
			}	
		}
		else if (e.getSource().equals(slot5)){
			myPanel.itemReceived(5);
			System.out.println("Plastic Bottle Insterted!");
			textArea.setText("Plastic Bottle Inserted!");
			try {
				test.addData("Plastic Bottle");
				
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			if(myPanel.Weight()<=100) {
				progressBar.setValue((int)myPanel.Weight());
			}else {
				progressBar.setValue(100);
				slot1.setVisible(false);
				slot2.setVisible(false);
				slot3.setVisible(false);
				slot4.setVisible(false);
				slot5.setVisible(false);
				textArea.setText("machine full");
				
				
				}
		}
		else if (e.getSource().equals(reset)) {
			System.out.println("Machine emptied");
			textArea.setText("machine emptied");
			slot1.setVisible(true);
			slot2.setVisible(true);
			slot3.setVisible(true);
			slot4.setVisible(true);
			slot5.setVisible(true);
			progressBar.setValue(0);	
			
			try {
				myPanel.removeItems();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "There is nothing in the machine");
			}
		    
		
}
		
		else if(e.getSource().equals(receipt)){
			try{
				myPanel.printReceipt();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please add something to the machine before printing the receipt");
			}
			
			
			
			
			
			
		
			
//	}else if (e.getSource().equals(slot7)) {
//		try {
//			rs = test.displayData();
//			while(rs.next()) {
//				System.out.println(rs.getString("name") +" " + rs.getString("date"));
//				textArea.append(rs.getString("name") + " " + rs.getString("date"));
//				
//			}
//			
//		} catch (Exception exception) {
//			
//			exception.printStackTrace();
//			System.out.println("there is an error");
//		} 
		
	}
		

}


	JButton slot1 = new JButton("Can"); 
	JButton slot2 = new JButton("Bottle"); 
	JButton slot3 = new JButton("Crate"); 
	JButton slot4 = new JButton("Paper Bag");
	JButton slot5 = new JButton("Plastic Bottle");
	JButton receipt = new JButton("Receipt");
//	JButton slot7 = new JButton("SQL Data");
	JButton reset = new JButton("Hard Reset");
	public static JProgressBar progressBar = new JProgressBar();
	JPanel panel = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	JTextArea textArea = new JTextArea();				
	
	
	

	
	
	public RecyclingGUI() {
		super();
		setTitle("Recycling Machine");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		slot1.setBounds(10, 11, 119, 23);
		slot1.setBackground(Color.ORANGE);
		slot1.addActionListener(this);
		panel.add(slot1);
		
		slot2.setBackground(Color.ORANGE);
		slot2.setBounds(10, 45, 119, 23);
		slot2.addActionListener(this);
		panel.add(slot2);

		slot3.setBackground(Color.ORANGE);
		slot3.setBounds(10, 80, 119, 23);
		slot3.addActionListener(this);
		panel.add(slot3);
		
		slot4.setBackground(Color.ORANGE);
		slot4.setBounds(10, 114, 119, 23);
		slot4.addActionListener(this);
		panel.add(slot4);
		
		slot5.setBackground(Color.ORANGE );
		slot5.setBounds(10, 150, 119, 23);
		slot5.addActionListener(this);
		panel.add(slot5);
		
		receipt.setBackground(Color.ORANGE );
		receipt.setBounds(10, 184, 119, 23);
		receipt.addActionListener(this);
		panel.add(receipt);
		
		reset.setBackground(Color.ORANGE);
		reset.setBounds(365,324,119,23);
		panel.add(reset);
		reset.addActionListener(this);
		
//		slot7.setBackground(Color.ORANGE );
//		slot7.setBounds(10, 218, 119, 23);
//		slot7.addActionListener(this);
//		panel.add(slot7);
		
		panel.setLayout(null);
		
		scrollPane.setBounds(165, 11, 300, 225);
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);
		progressBar.setStringPainted(true);
		
		panel.add(progressBar);
		progressBar.setBounds(10,325,119,23);
		
	
		
		
		panel.repaint();
		
		
		
	
	}
	
	public static void main(String [] args ) { 
		
		
		RecyclingGUI myGUI = new RecyclingGUI(); 
		myGUI.setVisible(true); 
		try {
			 System.out.println("Starting Server..."); 
		   WebServer server = new WebServer(80);
		   server.addHandler("sample", myGUI.myPanel);
		   server.start();
		  } catch (Exception exception) {
		   System.err.println("JavaServer: " + exception);
		   }
	}
	
	

	@Override
	public void print(String str) {
		System.out.println(str);
		textArea.setText(str);
		
		
	}
}