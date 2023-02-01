@API
Feature: UNSUCCESSFUL REGISTRATION:- As a new user I can't register with wrong credentials

  @Smoke
  Scenario: Unsuccessful register a user without password

    Given the endpoint for register user
    When post request with email "sydney@fife" and empty password has been sent
    Then the response must contain error: "Missing password"
