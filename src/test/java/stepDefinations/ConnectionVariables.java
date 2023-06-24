package stepDefinations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ConnectionVariables {
	
	FileInputStream file;
	Properties property;
	String url;
	String username;
	String password;
	
	public ConnectionVariables() {
		
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\constants.properties");
			property = new Properties();
			try {
				property.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		url = property.getProperty("dbUrl");
		username = property.getProperty("dbUsername");
		password = property.getProperty("dbPassword");
		
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
