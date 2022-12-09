@StarWars
Feature: To Perform Star wars API test

  @sw1
  Scenario: Create first Star API
    Given I go to Star wars homepage
    When  I send out the test Star wars API
    Then I get the proper response


    @sw2
    Scenario: I will run the planets API
      Given I go back to Star wars homepage
      When I get the Planet endpoint
      Then I get the planet API response
