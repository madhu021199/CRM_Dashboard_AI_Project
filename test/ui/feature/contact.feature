Feature: Create Contact

  @Positive @E2E @Regression
  Scenario: User logs in and successfully creates a new contact through both wizard steps
    Given the user is logged into the CRM application
    And the left navigation panel displays a "CONTACT" menu item
    When the user clicks the "CONTACT" menu item
    Then the Contacts page is displayed
    When the user clicks the "+ Create Contact" button
    Then the "Create Contact" panel opens on step 1, "Contact Information"
    When the user enters "Meera" in the "First name" field
    And the user enters "Nair" in the "Last name" field
    And the user enters "ashw@gmail.com" in the "Email" field
    And the user leaves "Contact owner" as the default value "demo"
    And the user enters "Marketing Manager" in the "Job title" field
    And the user enters "+8801700123456" in the "Phone number" field
    And the user enters "Marketing" in the "Department" field
    And the user selects "Retail" from the "Industry" dropdown
    And the user clicks the "Next" button
    #And the wizard advances to step 2, Contact Address
    And the user clicks the "Submit" button
    Then the contact creation message "Contact Created Successfully" is displayed
    And the user is redirected to the Contact Details page
    And a new row for "Meera Nair" is visible in the Contacts list
    And the row shows Email "ashw@gmail.com" and Phone number "+8801700123456"

  @Negative @E2E @Regression
  Scenario: User logs in but cannot create a contact when mandatory fields are blank or the email is already in use
    Given the user is logged into the CRM application
    And the left navigation panel displays a "CONTACT" menu item
    When the user clicks the "CONTACT" menu item
    Then the Contacts page is displayed
    When the user clicks the "+ Create Contact" button
    Then the "Create Contact" panel opens on step 1, "Contact Information"
    When the user leaves the "First name" field blank
    And the user leaves the "Email" field blank
    And the user clicks the "Next" button
    Then the wizard does not advance to step 2, "Contact Address"
    And the validation error "First Name is Required." is displayed under the "First name" field
    And the validation error "Please Enter The Email!" is displayed under the "Email" field
    And the user remains on step 1, "Contact Information"
     When the user enters "jon" in the "First name" field
    And the user enters "K" in the "Last name" field
    And the user enters "sriramkumar0412@gmail.com" in the "Email" field, which already belongs to an existing contact
    And the user enters "+917845212535" in the "Phone number" field
    And the user clicks the "Next" button
    And the validation error "Email Is Available" is displayed under the "Email" field
    And the user remains on step 1, "Contact Information"
    And no contact record is created
    And no new row appears in the Contacts list
