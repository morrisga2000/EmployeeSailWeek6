package com.practiceWithJSON;


import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


/**
 * 
 * Sample class that makes simple request to get all employees
 * @author Gregory Morris
 *
 */


public class implementUpdate {
	
	/**
	 * The URL of the API we want to connect to
	 */
	protected static String endpoint = " http://localhost:1337/employee/";
	
	/**
	 * The character set to use when encoding URL parameters
	 */
		protected static String charset = "UTF-8";
	
	/**
	 * API key used for making requests to API
	 */
		protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String yesOrNoChoice = null;
		String checkInput = null;
		String firstName = null;
		String lastName = null;
		String email =null;
		String homePhone = null;
		String password = null;
		String cellPhone = null;
		boolean selectionFN = false;
		boolean selectionLN = false;
		boolean selectionEmail = false;
		
		System.out.println("What is the integer of the employee number that you wish to UPDATE?");
		checkInput = scan.nextLine();
		String regex = "^[a-zA-Z]+$";
			if (checkInput.matches(regex)) {
				System.out.println("Error! Invalid integer value.");
				System.out.println("Please enter the integer value for the employee id number that you would like to select to view.");
				checkInput = scan.nextLine();
			}
			int employeeID = Integer.parseInt(checkInput);
		try {
			
	
			// The return type of the response xml | json
			String returnType = "json";
			
			// The first name of the employee
			System.out.println("Would you like the update the First Name: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is the the First Name?");
			firstName = scan.nextLine();
			selectionFN = true;
			}
			
			//The last name of the employee
			System.out.println("Would you like the update the Last Name: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is the Last Name?");
			lastName = scan.nextLine();
			selectionLN = true;
			}
			
			// the email address of the employee
			System.out.println("Would you like the update the e-mail address: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is new e-mail address that you would like update to?");
			email = scan.nextLine();
			selectionEmail = true;
			}
			
			// the home phone number of the employee  ###-###-####
			System.out.println("Would you like the update the home phone number: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is the new home phone number that you would like update to?");
			homePhone = scan.nextLine();
			}
			
			// the cell phone number of the employee  ###-###-####
			System.out.println("Would you like the update the cell phone number: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is the new cell phone that you would like update to?");
			cellPhone = scan.nextLine();
			}
			// the password of the employee, with 1 Upper-Case, 1 lower-case, 1 number, minimum 8 characters
			System.out.println("Would you like the update the password: (y)es/(n)o");
			yesOrNoChoice = scan.nextLine();
			if (yesOrNoChoice.toUpperCase().contains("Y"))
			{
			System.out.println("What is the new password that you would like update to?");
			password = scan.nextLine();
			}
			
			String active = "1";

			// creates the url parameters as a string encoding them with the defined charset
			String queryString = String.format("active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
				//	URLEncoder.encode(key, charset)
			);
			
			
			// creates a new URL out of the endpoint, returnType and queryString
			URL employeeInstructions = new URL(endpoint + employeeID + "?" + queryString); 
					 //+ returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeInstructions.openConnection();
			connection.setRequestMethod("PUT");
			
			// if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			String read;
			// loop of buffer line by line until it returns null meaning there are no more lines
			while ((read =br.readLine()) != null) {
				// print out each line to the screen
				read.split(",");
				System.out.println(read);
			}
			
			// close connection to API
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}   // end of main method
	
}  // end of class