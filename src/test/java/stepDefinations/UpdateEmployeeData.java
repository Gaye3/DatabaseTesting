package stepDefinations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateEmployeeData {
	
	Connection connection;
	Statement statement;
	ResultSet rs;
	
	@Given("Establish Sql connection")
	public void establish_sql_connection() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Accenture_employee_data","root","Testing@31");
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@When("user update the data of Employee {string} {string}")
	public void user_update_the_data_of_employee(String phoneNo,String employee) {
	    String updateData = "UPDATE Employees SET phone_no='"+phoneNo+"' WHERE employee_id='"+employee+"'";
	    try {
	        statement.execute(updateData);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Then("user gets recently updated data {string}")
	public void user_gets_recently_updated_data(String string) {
		String getData = "select * from Employees where employee_id="+string+";";
		try {
			 rs = statement.executeQuery(getData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the updated column {string}")
	public void validate_the_updated_column(String phoneNo) {
		boolean result = false;
		try {
			while(rs.next()) {
				if(phoneNo.equals(rs.getString("phone_no"))) {
					result = true;
				}
			}
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("close the connection")
	public void close_the_connection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
