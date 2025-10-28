Feature: Login Feature

  Scenario: Successful login with valid credentials
    Given I open Login Page
    When I enter email "wiley.griffin@testpro.io"
    And  I enter password "KUgY8Y1p"
    And I submit
    Then I am logged in