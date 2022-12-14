@API
Feature: USER DATA:- As an admin I can get and update information about users

  @Smoke
  Scenario: User data must contains required data

    Given the endpoint for sending requests about a list of users
    When get request has been sent
    Then the response must contain an avatar containing an ID for each user
    Then the response must contain an email ending with "@reqres.in" for each user

  @Extended
  Scenario: Admin update user information

    Given the endpoint for update user information
    When update request has been sent
    Then the response must contain user update time

