@APITESTFRAME
Feature: To verify API Automation with REST ASSURED

  @APITEST1
  Scenario: To verify Rest Service - GET method
    Given I want to execute getUserList Endpoint
    When I submit the GET request
    Then I should get 200 response code

    @APITEST2
    Scenario: To verify REST service with query parameter - GET method
      Given I want to execute getUserList Endpoint
      When I submit the GET request
      Then I should get page no. 2

