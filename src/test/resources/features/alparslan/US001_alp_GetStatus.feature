Feature: US001 - Alp - GET Status

  @WithoutToken
  Scenario: TC01_Should verify the status
    When alp User sends GET Request for status of API
    Then alp verifies status code is 200
    And alp validates response body
