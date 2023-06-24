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

public class DeleteEmployeeData {
	
	Connection connection;
	Statement statement;
	ResultSet rs;
	
	@Given("Establish Mysql connection")
	public void establish_mysql_connection() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Accenture_employee_data","root","Testing@31");
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@When("user delete a record of Employee {string}")
	public void user_delete_a_record_of_employee(String employeeID) {
		String deleteData = "DELETE FROM Employees WHERE employee_id='"+employeeID+"'";
	    try {
	        statement.execute(deleteData);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Then("validate the row is deleted {string}")
	public void validate_the_row_is_deleted(String employeeID) {
		String getData = "select * from Employees where employee_id="+employeeID+";";
		try {
			 rs = statement.executeQuery(getData);
			 Assert.assertFalse(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("close the sql connection")
	public void close_the_sql_connection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
