@WithoutToken @status
Feature:
  Scenario:GET Status
    Given User send GET request for the status code
    Then Verify the status code equals to 200

