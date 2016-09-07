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


public class implementCreate {
	
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

		try {
			
			// The first name of the employee
			System.out.println("What is the the First Name?");
			String firstName = scan.nextLine();
			
			//The last name of the employee
			System.out.println("What is the Last Name?");
			String lastName = scan.nextLine();
			
			// The return type of the response xml | json
			// String returnType = "json";
			
			// the email address of the employee
			System.out.println("What is the e-mail address?");
			String email = scan.nextLine();
			
			// the home phone number of the employee  ###-###-####
			System.out.println("What is the home phone number?");
			String homePhone = "111-222-3333";
			
			// the cell phone number of the employee  ###-###-####
			System.out.println("What is the cell phone?");
			String cellPhone = "123-456-1234";
			
			// the password of the employee, with 1 Upper-Case, 1 lower-case, 1 number, minimum 8 characters
			System.out.println("What is the password that you would like to create?");
			String password = "Passw0rd";
			
			String active = "1";
			
			// creates the url parameters as a string encoding them with the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
				//	URLEncoder.encode(key, charset)
			);
			
			// creates a new URL out of the endpoint and queryString
			URL employeeInstructions = new URL(endpoint + "?" + queryString); 
					 //+ returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeInstructions.openConnection();
			connection.setRequestMethod("POST");
			
			// if we did not get a 201 throw an exception
			if (connection.getResponseCode() != 201) {
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