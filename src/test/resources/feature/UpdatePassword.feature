Feature: Update Password

  Scenario: Registered user can update password and log in with the new password
    Given I get the user's current password hash from the database
    And I log in with the current password
    Then I should be logged in successfully

    When I click on the student profile icon
    Then I should be on the Profile and Preferences page

    When I update the password from current password to new password
    Then I should see the profile updated message
    And the password hash in the database should be changed

    When I log out
    And I log in with the new password
    Then I should be logged in successfully

    When I click on the student profile icon
    Then I should be on the Profile and Preferences page

    When I update the password from new password back to current password
    Then I should see the profile updated message
    And the password hash in the database should be changed again

    When I log out
    And I log in with the current password
    Then I should be logged in successfully