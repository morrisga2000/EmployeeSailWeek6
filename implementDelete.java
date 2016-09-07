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


public class implementDelete {
	
	/**
	 * The URL of the API we want to connect to
	 */
	protected static String endpoint = " http://localhost:1337/employee";
	
	/**
	 * The character set to use when encoding URL parameters
	 */
		protected static String charset = "UTF-8";
	
	/**
	 * API key used for making requests to API
	 */
	//	protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String checkInput = null;
		
		System.out.println("What is the integer of the employee number that you wish to DELETE?");
		checkInput = scan.nextLine();
		String regex = "^[a-zA-Z]+$";
			if (checkInput.matches(regex)) {
				System.out.println("Error! Invalid integer value.");
				System.out.println("Please enter the integer value for the employee id number that you would like to select to view.");
				checkInput = scan.nextLine();
			}
			int employeeID = Integer.parseInt(checkInput);
		try {
	/*		
			// The first name of the employee
			String firstName = "Jett";
			
			//The last name of the employee
			String lastName = "Windsor";
			
			// The return type of the response xml | json
			String returnType = "json";
			
			// the email address of the employee
			String email = "jett-windsor@gmail.com";
			
			// the home phone number of the employee  ###-###-####
			String homePhone = "512-333-7118";
			// the cell phone number of the employee  ###-###-####
			String cellPhone = "512-345-9999";
			// the password of the employee, with 1 Upper-Case, 1 lower-case, 1 number, minimum 8 characters
			String password = "WWJD2u4now"; 
			
			// creates the url parameters as a string encoding them with the defined charset
			String queryString = String.format("key=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(key, charset)
			);   */
			
			// creates a new URL out of the endpoint, returnType and queryString
			URL employeeInstructions = new URL(endpoint + "/" + employeeID); 
					 //+ returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeInstructions.openConnection();
			connection.setRequestMethod("DELETE");
			
			// if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			// loop of buffer line by line until it returns null meaning there are no more lines
			while (br.readLine() != null) {
				// print out each line to the screen
				System.out.println(br.readLine());
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