Feature: US-12345 - Search an employee in HRMS

  Background:
    * user enters valid admin credentials
    * user clicks on login button
    * user clicks on PIM button
    * user navigates to employee list page


  Scenario: Search an employee by ID
    * user enters valid employee ID
    * user clicks on search button
    * user is able to see employee information

    Scenario: Search an employee by name
      * user enters valid employee name
      * user clicks on search button
      * user is able to see employee information
