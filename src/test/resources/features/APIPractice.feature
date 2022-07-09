Feature: This feature covers all practice steps

  Scenario: Getting all votes
    Given a request is prepared to get all the votes
    When a GET call is made to get all the votes and object is created to store them
    Then the status code is 200 and response's length more than 0

    Scenario: Getting random vote
      Given a request is prepared to get a vote with random id
      When a GET call is made to get a vote with random id
      Then the status code is 200 and response is not empty

      Scenario: