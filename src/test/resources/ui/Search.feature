@UI
Feature: As a user I want to get ability to search for articles

  @Extended
  Scenario: Docs sections should contains appropriate columns
    Given the user opens Cucumber website
    And the user clicks on the "ALLOW ALL COOKIES" button
    And the user enters the word "Announces" in the search field
    And the user performs a search
    And the user filters search results by the "Blog" parameter
    When the user opens the first link
    Then the page title and links are the same


