@API
Feature: YEARS:- As a user, I can get list of years

  @Smoke
  Scenario: User get list of sorted years

    Given the endpoint for get sorted years
    When get request for sorted years has been sent
    Then the response must contain a list of years sorted in ascending order
