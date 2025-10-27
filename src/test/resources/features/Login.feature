Feature: Login Feature

  //This is a comment

  Scenario:
    Given I open browser
    And I open Login Page
    When I enter email "shynar@testpro.io"
    And I enter password "Javashynar890@"
    And I submit
    Then I am logged in