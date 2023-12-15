Feature: US09 - Alp - Api Authentication

  @WithoutToken
  Scenario: TC01_Register API Client
    When alp User sends POST request to register and generate token
    Then alp Verifies status code is 201
    And alp validates response body and the token



  @WithoutToken
  Scenario: TC02_Register API client with registered credentials
    When alp User creates a new API client to have registered credentials
    And alp User sends POST request to register with registered credentials
    Then alp Verifies status code is 409
    And alp validates response payload has error message