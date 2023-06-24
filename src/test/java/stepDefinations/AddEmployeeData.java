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

public class AddEmployeeData{
	
	Connection connection;
	Statement statement;
	ResultSet rs;
	
	@Given("Establishing sql connection")
	public void establishing_sql_connection() {
		ConnectionVariables variable = new ConnectionVariables();
		try {
			connection=DriverManager.getConnection(variable.url,variable.username,variable.password);
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@When("user adds data to employee {string} {string} {string} {string} {string}")
	public void user_adds_data_to_employee(String employeeID, String name, String phoneNo, String jobId, String departmentId) {
	    String addData = "INSERT INTO Employees (employee_id, employee_name, phone_no, job_id, department_id) " +
	            "VALUES ('" + employeeID + "', '" + name + "', '" + phoneNo + "', '" + jobId + "', '" + departmentId + "');";
	    try {
	        statement.execute(addData);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Then("user gets the recent added data from employee {string}")
	public void user_gets_the_recent_added_data_from_employee(String string) {
		String getData = "select * from Employees where employee_id="+string+";";
		try {
			 rs = statement.executeQuery(getData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Then("validate the details added to employee {string} {string} {string} {string} {string}")
	public void validate_the_details_added_to_employee(String string, String string2, String string3, String string4, String string5) {
		boolean result = false;
		try {
			while(rs.next()) {
				
				if(
				string.equals(rs.getString("employee_id")) &
				string2.equals(rs.getString("employee_name")) &
				string3.equals(rs.getString("phone_no")) &
				string4.equals(rs.getString("job_id")) &
				string5.equals(rs.getString("department_id"))) {
					result = true;
					System.out.println(string+" "+string2+" "+string3+" "+string4+" "+string5);
				}
			}
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@When("user adds data to jobs {string} {string} {string}")
	public void user_adds_data_to_jobs(String jobId, String jobTitle, String salary) {
	    String addData = "INSERT INTO Jobs (job_id, job_title, salary) VALUES ('" + jobId + "', '" + jobTitle + "', '" + salary + "');";
	    try {
	        statement.execute(addData);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Then("user gets the recent added data from jobs {string}")
	public void user_gets_the_recent_added_data_from_jobs(String string) {
		String getData = "select * from Jobs where job_id="+string+";";
		try {
			 rs = statement.executeQuery(getData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the details added to jobs {string} {string} {string}")
	public void validate_the_details_added_to_jobs(String string, String string2, String string3) {
		boolean result = false;
		try {
			while(rs.next()) {
				
				if(
				string.equals(rs.getString("job_id")) &
				string2.equals(rs.getString("job_title")) &
				string3.equals(rs.getString("salary"))) {
					result = true;
					System.out.println(string+" "+string2+" "+string3);
				}
			}
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@When("user adds data to department {string} {string} {string}")
	public void user_adds_data_to_department(String departmentId, String departmentName, String location) {
	    String addData = "INSERT INTO Departments (department_id, department_name, location) " +
	            "VALUES ('" + departmentId + "', '" + departmentName + "', '" + location + "');";
	    try {
	        statement.execute(addData);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Then("user gets the recent data from department {string}")
	public void user_gets_the_recent_data_from_department(String string) {
		String getData = "select * from Departments where department_id="+string+";";
		try {
			 rs = statement.executeQuery(getData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("validate the details added to department {string} {string} {string}")
	public void validate_the_details_added_to_department(String string, String string2, String string3) {
		boolean result = false;
		try {
			while(rs.next()) {
				
				if(
				string.equals(rs.getString("department_id")) &
				string2.equals(rs.getString("department_name")) &
				string3.equals(rs.getString("location"))) {
					result = true;
					System.out.println(string+" "+string2+" "+string3);
				}
			}
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Then("close sql connection")
	public void close_sql_connection() {
	    try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
