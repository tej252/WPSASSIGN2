package vasavi;
import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class JDBCCachedRowSet {

	public static void main(String[] args) throws Exception
	{


		public class MySqlConnection 
		{
			public Connection MySqlConnect() throws Exception{
				String url = "jdbc:mysql://localhost:3306/test";
				String username = "root";
				String password = "";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);
				return con;
				}
		}
		  
	       Connection con = new MySqlConnection().MySqlConnect();
		  con.setAutoCommit(false);
		  String url = "jdbc:mysql://localhost:3306/test";
		  String username = "root";
		  String password = "";
		  RowSetFactory factory = RowSetProvider.newFactory();
	     	  CachedRowSet rowSet = factory.createCachedRowSet();
		  rowSet.setUrl(url);
		  rowSet.setUsername(username);
		  rowSet.setPassword(password);
		  rowSet.setCommand("select * from user");
	      rowSet.execute();
	    
	      int choice;
	      String[] array ;
	      String[] attribute = {"Name", "emailId", "dob in dd-mm-yy"};
	      Scanner sc = new Scanner(System.in);
	      System.out.println("Enter your choice : ");
	      System.out.println("1. Insert data");
	      System.out.println("2. Display data");
	      System.out.println("3. Update data");
	      choice = sc.nextInt();
	      
	      
	      switch(choice) {
	      case 1:
	    	array = new String[10];
	    	for(int i = 0; i< 3; i++) {
	    		System.out.println("Enter "+attribute[i]+" : ");
	    		array[i] = sc.next();
	    	}
	    	System.out.println("Enter mobile number");
	    	int mobile = sc.nextInt();
	    	rowSet.moveToInsertRow();
	  		rowSet.updateString(1, array[0]);
	  		rowSet.updateString(2, array[1]);
	  		rowSet.updateInt(3, mobile);
	  		rowSet.updateString(4, array[2]);
	  	    rowSet.insertRow();
	  	    rowSet.moveToCurrentRow();
	  	    rowSet.acceptChanges(con);
	  	    System.out.println("Row Inserted Successfully");
  	            System.out.println("");
	  	    break;
	      case 2:
	    	int mobileno;
	    	System.out.println("Enter mobile number :");
	    	mobileno = sc.nextInt();
	    	rowSet.setCommand("select * from user where mobile="+mobileno);
	  	    rowSet.execute();
	  	    System.out.println("Details of given phone number : ");
	  	    while(rowSet.next()) {
		         System.out.print("User Name: "+rowSet.getString("name")+", ");
		         System.out.print("EmailId: "+rowSet.getString("email")+", ");
		         System.out.print("MobileNo: "+rowSet.getInt("mobile")+", ");
		         System.out.print("DOB: "+rowSet.getString("dob"));
		         System.out.println("");
	  	    }
	  	    break;
	      case 3:
	    	  String name, email;
	    	  System.out.println("Enter the name whose email should be changed");
	    	  name = sc.next();
	    	  int flag = 0;
	    	  System.out.println("Enter the changed email id");
	    	  email = sc.next();
	    	  while(rowSet.next()) {
		          if(rowSet.getString("name").equals(name)) {
		             rowSet.updateString("email", email);
		             rowSet.updateRow();
		             flag = 1;
		          }
		       } 
		      rowSet.acceptChanges(con);
		      if(flag == 1)
		    	  System.out.println("Email id changed to "+email+" successfully");
		      else
		    	  System.out.println("Couldn't change the email id");
		      break;
	  	  default :
	  		  break; 
	      }
	}
}