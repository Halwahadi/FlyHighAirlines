/*
 * Welcome to the FlyHigh Airlines Reservation System, where we give you quick and easy access to
 *  the best flights at the best prices.
 *  
 *   @author T'Avvion Jones
 *   July/August 2019
 */
package FHA;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Reservations {
	
	//connect to database
	private static String url = "jdbc:mysql://127.0.0.1:3306/FlyHigh?autoReconnect=true&useSSL=false";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String pass = "Muffin99";
	private static DatabaseConnection db = new DatabaseConnection(url, driver, user, pass);
	
	static Scanner sc = new Scanner (System.in);
		
	public static void main (String [] args) {
	
		String option = null;
		while (true) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			   System.out.println(dtf.format(now));
			System.out.println("Welcome to Fly High Airlines!\n"
					+ "Passenger Info (1), Book Flight(2), Get Ticket(3), or Exit(X)");
			option = sc.next();
			if(option.equalsIgnoreCase("1")) {
				passenger();
			}else if(option.equalsIgnoreCase("2")) {
				//flight();
			}else if(option.equalsIgnoreCase("3")) {
				//ticket();
			}else if(option.equalsIgnoreCase("X")) {
				break;
			}else {
				System.out.println("Invalid Option. Try again...");
			}
		}
	}
	
	public static void passenger() {
		String option = null;
		Passenger p = new Passenger();
		
		while (true) {
			System.out.println("----------Passenger----------");
			System.out.println("Enter new passenger info (1)\tRetrieve passenger info (2)\t"
					+ "Update passenger info(3) \tDelete Passenger(4)\t Exit(X)");
			option = sc.next();
			if(option.equalsIgnoreCase("1")) {
				p.addPass();
			}else if(option.equalsIgnoreCase("2")) {
				System.out.println("Enter Passenger ID:");
				int pass_id = sc.nextInt();
				p.retrievePass(pass_id);
			}else if(option.equalsIgnoreCase("3")) {
				System.out.println("Enter Passenger ID:");
				int pass_id = sc.nextInt();
				p.updatePass(pass_id);
			}else if(option.equalsIgnoreCase("4")) {
				System.out.println("Enter Passenger ID:");
				int pass_id = sc.nextInt();
				p.deletePass(pass_id);
			}else if(option.equalsIgnoreCase("X")) {
				break;
			}else {
				System.out.println("Invalid Option. Try again...");
			}
		}
	}

}
