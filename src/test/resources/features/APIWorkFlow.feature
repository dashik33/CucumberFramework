Feature: This feature covers all the API-related scenarios

  Background:
    Given a JWT token is generated

  @api
  Scenario: Creating an employee
  Given a request is prepared to create an employee
  When a POST call is made to create an employee
  Then the status code of the created employee is 201
  And the employee created contains key "Message" and value "Employee Created"
  And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @api
  Scenario: Getting the newly created employee
    Given a request is prepared to get the employee
    When a GET call is made to retrieve the created employee
    Then the status code for this employee is 200
    And the employee has employee ID "employee.employee_id" must match with globally stored employee ID
    And the retrieved data at "employee" object matches the data used to create the employee having employee ID "employee.employee.id"
    |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
    |Mary         |Russell     |B              |Male      |2006-06-06  |Active    |Developer    |

  @json
  Scenario: Creating an employee using json object
    Given a request is prepared to create an employee via json object
    When a POST call is made to create an employee
    Then the status code of the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @dynamic
  Scenario: Creating an employee using dynamic payload
    Given a request is prepared to create an employee via dynamic payload "George", "Washington", "Jr", "M", "1999-07-09", "Probation", "President"
    When a POST call is made to create an employee
    Then the status code of the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls