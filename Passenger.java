package FHA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Passenger {
	Scanner sc = new Scanner(System.in);
	DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://127.0.0.1:3306/FlyHigh?autoReconnect=true&useSSL=false", "com.mysql.jdbc.Driver", "root",
				"Muffin99");
	//private int id;
	//private String first_name, last_name, birthday, sex,
		//phone_number, address_number, street_name, city, state, zipcode;
	public Passenger() {
		
	}
	public boolean addPass() {
		
		System.out.println("ID number:");
		int pass_id = sc.nextInt();
		System.out.println("First name:");
		String first_name = sc.next();
		System.out.println("Last name:");
		String last_name = sc.next();
		System.out.println("DOB (YYYY-MM-DD):");
		String birthday = sc.next();
		System.out.println("Sex (M/F):");
		String sex = sc.next();
		System.out.println("Phone Number:\n\tPlease do not include any special characters (i.e '-' '()'etc).");
		String phone_number = sc.next();
		System.out.println("Address Number:");
		String address_number = sc.next();
		System.out.println("Street Name:");
		String street_name = sc.next();
		System.out.println("City:");
		String city = sc.next();
		sc.next();
		System.out.println("State:");
		String state= sc.next();
		System.out.println("5-digit Zipcode:");
		String zipcode = sc.next();
		
		//check if passenger exist
		ResultSet rs;
        String sql = "SELECT pass_id FROM passenger";
        rs = connect.execute(sql);
        try {
            while(rs.next())
            {
                int id = rs.getInt("pass_id");
                if(id == pass_id)
                    return false;
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
		//add to database
		String sqlStatement = ("INSERT INTO passenger VALUES(" +pass_id+ ", '"+first_name+ "', '"+last_name+ "', '"+birthday+ "', '"+sex+ "', '"
				+phone_number+ "', '"+address_number+ "', '"+street_name+ "', '"+city+ "', '"+state+ "', '"+zipcode+"')");
		
		try {
			connect.executeUpdate(sqlStatement);
			System.out.println("Passenger added.");
			return true; 
		} catch(Exception exe) {
			exe.printStackTrace();
			return false;
		}
	}
	public void retrievePass(int pass_id) {
		//Retrieve passenger by searching for the Passenger's unique ID
		ResultSet rs;
        String sql = "SELECT * FROM passenger WHERE pass_id LIKE '" +pass_id+ "'";
        rs = connect.execute(sql);
        try {
            while(rs.next())
            {
                System.out.println(rs.getInt("pass_id") +" "+ rs.getString("first_name") +" "+ rs.getString("last_name") +" "+
                		rs.getString("birthday") +" "+ rs.getString("sex") +" "+ rs.getString("phone_number") +" "+ rs.getString("address_number")
                		+" "+ rs.getString("street_name") +" "+ rs.getString("city") +" "+ rs.getString("state") +" "+ rs.getString("zipcode"));
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
	}
	
	public void updatePass(int pass_id) {
		retrievePass(pass_id);
		String columnNames [] = {"pass_id", "first_name", "last_name", "birthday", "sex", 
				"phone_number", "address_number", "street_name", "city", "state", "zipcode"};
		
		System.out.println("Which column number would yo like to update?\n (Enter 1 column number at a time. The left most column being column 1.)");
		int columnNumber = sc.nextInt();
		String column = columnNames[columnNumber - 1];
		System.out.println("Enter new info for column:");
		String data = sc.next();
		
		String sql = "UPDATE passenger SET " +column+ " = '" +data+ "' WHERE pass_id = " +pass_id;
		try {
			connect.executeUpdate(sql);
			System.out.println("Passenger updated.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deletePass(int pass_id) {
		retrievePass(pass_id);
		String sql = "DELETE FROM passenger WHERE pass_id = " + pass_id;
		System.out.println("Are you sure you would like to delete?(Y/N)\n Upon delete, the information will be permanently deleted.");
		String answer = sc.nextLine();
		if (answer.equalsIgnoreCase("y")) {
			try {
				connect.executeUpdate(sql);
				System.out.println("Passenger permanently deleted.");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
