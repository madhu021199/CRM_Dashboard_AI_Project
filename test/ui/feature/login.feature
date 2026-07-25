Feature: CRM Login and Logout

  @Positive @E2E @Regression
  Scenario: End-to-End Positive Login & Logout Flow
    Given the user opens the browser and navigates to the CRM application URL
    Then the CRM login page should be displayed with Username, Password fields and a Sign In button
    When the user types a password into the Password field and clicks the "eye" (show/hide) icon
    Then the password should toggle between masked and plain text view
    When the user enters a valid username and a valid password
    And clicks the "Sign in" button
    Then the user should be successfully redirected to the CRM dashboard page
    When the user clicks the "Logout" button on the dashboard
    Then the user should be redirected to the CRM login page
    And the session should be terminated


  @Negative @E2E @Regression
  Scenario: End-to-End Negative Login Validation Flow
    Given the user is on the CRM login page
    When the user leaves both the Username and Password fields empty and clicks "Sign in"
    Then validation error messages should be displayed for both fields
    And the user should remain on the login page
    When the user leaves the Username field empty, enters a valid password, and clicks "Sign in"
    Then a validation error message "Username is required" should be displayed
    And the user should remain on the login page
    When the user enters a valid username, leaves the Password field empty, and clicks "Sign in"
    Then a validation error message "Password is required" should be displayed
    And the user should remain on the login page
    When the user enters an invalid/non-existent username with a valid password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page
    When the user enters a valid username with an incorrect password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page
    When the user enters both an invalid username and an invalid password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page
