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
    #When the user enters a valid username, leaves the Password field empty, and clicks "Sign in"
    #Then a validation error message "Password is required" should be displayed
    #And the user should remain on the login page
    When the user enters an invalid/non-existent username with a valid password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page
    When the user enters a valid username with an incorrect password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page
    When the user enters both an invalid username and an invalid password and clicks "Sign in"
    Then an error pop-up with message "Invalid username or password" should be displayed
    And the user should remain on the login page

  @EdgeCase  @Security  @E2E  @Regression
  Scenario: End-to-End Edge Case Login Flow
    Given the user is on the CRM login page
    When the user enters a SQL injection payload (e.g. ' OR '1'='1) in the Username or Password field and clicks "Sign in"
    Then the system should reject the input and show an "Invalid username or password" error
    #And no unauthorized access or database error should occur
    When the user enters a script tag (e.g. <script>alert('xss')</script>) into the Username field and clicks "Sign in"
    Then the input should be sanitized or rejected
    #And no script execution should occur
    When the user enters the correct username/password with different letter casing (e.g. "DEMO" instead of "demo") and clicks "Sign in"
    Then the system should behave per the defined case-sensitivity rule (login successfully if case-insensitive, or show an invalid credentials error if case-sensitive)
    When the user enters an extremely long string (500+ characters) into the Username or Password field and clicks "Sign in"
    Then the system should handle it gracefully — either truncate, show a max-length validation error, or reject without crashing
    When the user enters special characters (e.g. @#$%^&*) in the Username/Password fields and clicks "Sign in"
    Then the system should validate/handle the input without errors or unexpected behavior
    Given the user successfully logs into the CRM dashboard
    When the user logs out and then clicks the browser "Back" button
    And should be redirected to the login page

