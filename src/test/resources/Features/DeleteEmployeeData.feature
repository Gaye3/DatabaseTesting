Feature: Delete employee Data
  		As a user
  		I want to delete employee deatails
  		So that the data is up to date.

  Scenario Outline: Validate employee details are removed from database.
    Given Establish Mysql connection
    When user delete a record of Employee "<employeeId>"
    Then validate the row is deleted "<employeeId>"
    Then close the sql connection

    Examples: 
      | employeeId |
      |    2062913 |
