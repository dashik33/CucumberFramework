Feature: Adding employees to HRMS application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    And user clicks on PIM button
    And user clicks on add employee option

  Scenario: Adding one employee from a feature file
    And user enters firstname, middlename and lastname
    And user clicks on save button
    Then the new employee is successfully added

  Scenario: Adding one employee using cucumber feature file
      And user enters "Henry", "Ivanovich" and "Pirogov"
      And user clicks on save button
      Then the new employee is successfully added

  Scenario Outline: Adding multiple employees
    #in scenario outline browser opens and closes as many times as number of data we provided. it works like a new
      # scenario for each data set
        And user provides "<firstName>", "<middleName>" and "<lastName>"
        And user clicks on save button
        Then the new employee is successfully added
        Examples:
        |firstName|middleName|lastName|
        |Ivan     |Ivanovich |Ivanov  |
        |Igor     |Igorevich |Igorev  |
        |Roman    |Romanovich|Romanov |

  Scenario: Add employee using data table
    # in datatable browser opens and closes only once. it works like one scenario
      When user provides multiple employee data and verify they are added
        |firstName|middleName|lastName|
        |Ivan     |Ivanovich |Ivanov  |
        |Igor     |Igorevich |Igorev  |
        |Roman    |Romanovich|Romanov |

    Scenario: Adding multiple employees from excel file
      When user adds multiple employees from excel file using "EmployeeData" sheet and verify they are added

  Scenario: Adding one employee using cucumber feature file
    And user enters "Wick", "John" and "A."
    And user grabs the employee ID
    And user clicks on save button
    And user query the database for the same employee ID
    Then user verifies the results
