Feature: Columns not allow null values

  Scenario Outline: Validate the columns which does not allow null values.
    Given Establish a database connection
    Then user gets the database metadata
    Then user retrieve the column metadata for the table "<tableName>"
    Then check if the column allows null values
    Then Close the database connection

    Examples: 
      | tableName   |
      | Jobs        |
      | Departments |
