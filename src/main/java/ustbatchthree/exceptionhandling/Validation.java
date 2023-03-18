package ustbatchthree.exceptionhandling;

/**
 * Hello world!
 *
 */


import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Validation {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name");
		String name = sc.nextLine();
		System.out.println("Enter Employee id");
		String eid = sc.next();
		System.out.println("Enter work Location");
		String loc = sc.next();
		System.out.println("Enter phone number");
		String num = sc.next();
		System.out.println("Enter email id");
		String mail = sc.next();
		System.out.println("Enter Permenemt adress");
		String adress = sc.next();

		try {
			validatePhoneNumber(num);
			System.out.println("Phone number is valid: " + num);
		} catch (InvalidPhoneNumberException e) {
			System.out.println(e.getMessage());
		}
	
	
	 try {
         validateEmail(mail);
         System.out.println("Email address is valid: " + mail);
     } catch (InvalidEmailException e) {
         System.out.println(e.getMessage());
     }
 
	try {
		validatename(name);
		System.out.println("Name is valied " + name);
	} catch(InvalidNameException e) {
		System.out.println(e.getMessage());}
	
	try {
	      validateEmployeeId(eid);
	      System.out.println("Valid employee ID!");
	    } catch (EmployeeIdValidationException e) {
	      System.out.println(e.getMessage());
	    }
	  }


	
	

	public static void validatePhoneNumber(String num) throws InvalidPhoneNumberException {
		if (!num.matches("\\d{10}")) {
			throw new InvalidPhoneNumberException("Invalid phone number: " + num);
		}

	}
	
	public static void validateEmail(String mail) throws InvalidEmailException {
        if (!mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new InvalidEmailException("Invalid email address: " + mail);
        }
    }
	
	public static void validatename(String name) throws InvalidNameException{
		if(name==null||name.isEmpty()) {
			throw new InvalidNameException("Name cannot be null: ");
		}
		 if (!name.matches("[a-zA-Z]+")) {
		      throw new InvalidNameException("Name can only contain letters.");
	    }}
	public static void validateEmployeeId(String eid) throws EmployeeIdValidationException, ClassNotFoundException {
	    if (eid == null || eid.isEmpty()) {
	      throw new EmployeeIdValidationException("Employee ID cannot be empty.");
	    }

	    if (!eid.matches("^E\\d{4}$")) {
	      throw new EmployeeIdValidationException("Employee ID must start with 'E' followed by 4 digits.");
	    }
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/prize","root","pass@word1");
		java.sql.Statement stmt=(java.sql.Statement) con.createStatement();
		String sql= "create table employee"+
				"(eid varchar(25) PRIMARY KEY,"+
				 "name VARCHAR(255) NOT NULL,"+
				 "phone VARCHAR(20) NOT NULL,"+
				 " email VARCHAR(255) NOT NULL)";
		stmt.executeUpdate(sql);
		String sql1="INSERT INTO employee (eid, name, phone, email) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		  pstmt.setString(1, eid);
//	      pstmt.setString(2,name);
//	      pstmt.setString(3,num);
//	      pstmt.setString(4,mail);
	      pstmt.executeUpdate();
	      System.out.println("Employee details inserted successfully.");
	}catch (SQLException e) {
        e.printStackTrace();
	}
}}
	
	