Feature: Add employee Data
  		As a user
  		I want to add employee deatails into the database
  		So that the data is secure

  Scenario Outline: Validate the employee details added to the database
    Given Establishing sql connection
    When user adds data to employee "<employeeID>" "<name>" "<phoneNo>" "<jobId>" "<departmentId>"
    Then user gets the recent added data from employee "<employeeID>"
    Then validate the details added to employee "<employeeID>" "<name>" "<phoneNo>" "<jobId>" "<departmentId>"
    When user adds data to jobs "<jobId>" "<jobTitle>" "<salary>"
    Then user gets the recent added data from jobs "<jobId>"
    Then validate the details added to jobs "<jobId>" "<jobTitle>" "<salary>"
    When user adds data to department "<departmentId>" "<departmentName>" "<location>"
    Then user gets the recent data from department "<departmentId>"
    Then validate the details added to department "<departmentId>" "<departmentName>" "<location>"
    Then close sql connection

    Examples: 
      | employeeID | name   | phoneNo    | jobId | departmentId | jobTitle         | salary | departmentName | location  |
      |      34568 | swathi | 9876543210 |  1008 |         2001 | Quality Enginner |  35000 | Testing        | Bangalore |
