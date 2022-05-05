Feature: Validation of login scenarios

  Background: #executes before every scenario

  Scenario: Admin login
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

  Scenario: Ess login
    When user enters valid ess credentials
    And user clicks on login button
    Then ESS user is successfully logged in

    @test
  Scenario: Invalid login
    When user enters valid invalid credentials
    And user clicks on login button
    Then user sees an error message