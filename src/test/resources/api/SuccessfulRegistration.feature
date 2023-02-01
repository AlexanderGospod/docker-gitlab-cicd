@API
Feature: SUCCESS REGISTRATION:- As a new user I can register

  @Smoke
  Scenario: Register a user successfully

    Given the endpoint for register user
    When post request with email "eve.holt@reqres.in" and password "pistol" has been sent
    Then the response must contain ID new user
    Then the response must contain token new user