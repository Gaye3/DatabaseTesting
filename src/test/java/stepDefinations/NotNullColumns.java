package stepDefinations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.DatabaseMetaData;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NotNullColumns {
	
	Connection connection;
	Statement statement;
	ResultSet columnsResultSet;
	DatabaseMetaData metaData;
	
	@Given("Establish a database connection")
	public void establish_a_database_connection() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Accenture_employee_data","root","Testing@31");
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("user gets the database metadata")
	public void user_gets_the_database_metadata() {
		 try {
			metaData = connection.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("user retrieve the column metadata for the table {string}")
	public void user_retrieve_the_column_metadata_for_the_table(String tableName) {
		 try {
			columnsResultSet = metaData.getColumns(null, null, tableName, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("check if the column allows null values")
	public void check_if_the_column_allows_null_values() {
		try {
			while (columnsResultSet.next()) {
			    String columnName = columnsResultSet.getString("COLUMN_NAME");
			    int nullable = columnsResultSet.getInt("NULLABLE");

			    if (nullable == DatabaseMetaData.columnNoNulls) {
			        System.out.println("Column '" + columnName + "' does not allow null values.");
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("Close the database connection")
	public void close_the_database_connection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
