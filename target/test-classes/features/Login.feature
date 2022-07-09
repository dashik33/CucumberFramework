Feature: Validation of login scenarios

  Background: #executes before every scenario

  @smoke
  Scenario: Admin login
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

  Scenario: Ess login
    When user enters valid ess credentials
    And user clicks on login button
    Then ESS user is successfully logged in

  Scenario: Invalid login
    When user enters invalid credentials
    And user clicks on login button
    Then user sees Invalid credentials error message

  Scenario: Empty username field
    When user enters password and leave username field empty
    And user clicks on login button
    Then user sees Username cannot be empty error message


  Scenario: Empty password field
    When user enters username and leave password field empty
    And user clicks on login button
    Then user sees Password cannot be empty error message