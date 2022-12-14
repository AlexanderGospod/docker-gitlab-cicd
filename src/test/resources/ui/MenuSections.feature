@UI
Feature: As a user I want to get ability to use menu sections

  @Smoke
  Scenario Outline: Docs sections should contains appropriate columns
    Given the user opens Cucumber website
    And the user clicks on the "ALLOW ALL COOKIES" button
    And the user clicks on the "Docs" section
    When the user clicks on the "<title>" item
    Then page with title "<title>" is displayed
    Examples:
      |title  |
      |Gherkin Syntax  |
      |Installation  |