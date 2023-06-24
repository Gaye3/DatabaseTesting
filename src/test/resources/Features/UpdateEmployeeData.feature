Feature: Update employee Data
  		As a user
  		I want to update employee deatails
  		So that the data is up to date.

  Scenario Outline: Validate employee details are updated in the database.
    Given Establish Sql connection
    When user update the data of Employee "<phoneNo>" "<employeeId>"
    Then user gets recently updated data "<employeeId>"
    Then validate the updated column "<phoneNo>"
    Then close the connection

    Examples: 
      | employeeId | phoneNo    |
      |      34566 | 2345678923 |
