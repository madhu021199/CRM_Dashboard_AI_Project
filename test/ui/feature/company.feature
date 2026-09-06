Feature: CRM Company Management

  @Positive @E2E @Regression
  Scenario: End-to-End Positive Company Creation Flow
    Given the user is logged into the CRM application
    When the user clicks on the "COMPANY" option in the left navigation panel
    Then the user is navigated to the Company listing page and "COMPANY" is shown as active in the navigation
    When the user clicks "Create Company"
    Then the "Create Company" pop-up opens showing the "Company Information" step
    When the user enters the unique Company Name "Nova Tech Solutions"
    And selects Industry as "Information Technology and Services"
    And selects Company Type as "Private"
    And enters a valid Website "https://www.novatech.com"
    And enters a valid Phone number "+91 9845012345"
    And clicks Next button
    Then no validation errors are displayed and the user proceeds to the "Billing Information" step
    And clicks submit button to create the New Company
    Then the company "Nova Tech Solutions" is created successfully
    And user validate the message as "Company Created Successfully"
    And "Nova Tech Solutions" appears in the Company list on the Company page with the details entered

  @Negative @E2E @Regression
  Scenario: End-to-End Negative Company Validation Flow
    Given the user is logged into the CRM application
    When the user clicks on the "Company" option in the left navigation panel
    Then the user is navigated to the Company listing page and "Company" is shown as active in the navigation
    When the user clicks "Create Company"
    Then the "Create Company" pop-up opens showing the "Company Information" step
    When the user clicks "Next" without entering a Company Name
    Then a mandatory field validation error is displayed for "Company Name" and the form is not submitted
#    When the user enters only blank spaces "   " into the Company Name field and clicks "Next"
#    Then the input is treated as empty and the mandatory field validation error is displayed again
#    When the user enters "GreenFields A", a Company Name that already exists, and clicks "Next"
#    Then a duplicate name validation error is displayed and the form is not submitted
#    When the user changes the Company Name to the unique value "Quantum Edge Pvt Ltd"
#    And leaves the "Industry" and "Company Type" dropdowns unselected
#    And clicks "Next"
#    Then validation errors are displayed for the unselected mandatory "Industry" and "Company Type" fields
#    When the user selects valid values for "Industry" and "Company Type"
#    And enters an invalid Website URL "htp//quantumedge"
#    And enters an invalid Phone number "ABC123"
#    And clicks "Next"
#    Then invalid format validation errors are displayed for both the "Website" and "Phone" fields
#    And the user remains on the "Company Information" step
#    And no company record is created or added to the Company list at any point in this flow
