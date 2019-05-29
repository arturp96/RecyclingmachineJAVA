package Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.w3c.dom.Text;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class SQL {
	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet displayData() throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name, date FROM logs");
		return res;
	}
	public ResultSet displayUser() throws ClassNotFoundException, SQLException{
		if(con == null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res1 = state.executeQuery("SELECT name, password FROM user");
		return res1;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:./../xml1.db");
		initialise();
		
	}

	private void initialise() throws SQLException {
		if(!hasData) {
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'logs'");
			if (!res.next() ) {
				System.out.println("Building the logs table with prepopulated values");
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE logs(id integer,"
						+"name varchar(60),"
						+"date varchar(60),"
						+"primary key(id));");
				
				
				Statement state3 =con.createStatement();
				state3.execute("CREATE TABLE user (id integer," +"name varchar(60),"+ "password varchar(60)," + "primary key(id));");
				
				
				
//
//				PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
//				prep.setString(2, "cunt");
//				prep.setString(3, "retard");
//				prep.execute();
////				
//				
//				
//				PreparedStatement prep2 = con.prepareStatement("INSERT INTO logs values(?,?,?);");
//				prep2.setString(2, "Bottle");
//				prep2.setString(3, "30");
//				prep2.execute();
//				
//				PreparedStatement prep3 = con.prepareStatement("INSERT INTO logs values(?,?,?);");
//				prep2.setString(2, "Crate");
//				prep2.setString(3, "30");
//				prep2.execute();
//				
//				PreparedStatement prep4 = con.prepareStatement("INSERT INTO logs values(?,?,?);");
//				prep2.setString(2, "Plastic Bottle");
//				prep2.setString(3, "30");
//				prep2.execute();
			}
		}
		
	}
	
	public void addData(String Name) throws ClassNotFoundException, SQLException {
		String Date = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss \n").format(Calendar.getInstance().getTime());
		if (con == null) {
			getConnection();
		}
		PreparedStatement prep = con.prepareStatement("INSERT INTO logs values(?,?,?);");
		prep.setString(2, Name);
		prep.setString(3, Date);
		prep.execute();
		
		
		
	} 
	public void addUsers(String Name, String Password) throws ClassNotFoundException, SQLException{
		if (con==null) {
			getConnection();
		}
		PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
		prep.setString(2,Name);
		prep.setString(3,Password);
		prep.execute();
		
		
	}
}



