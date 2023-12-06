Feature: US09_Api Authentication

  @WithoutToken
  Scenario: TC01_Register API Client
    When alp User sends POST request to register and generate token
    Then alp Verifies status code is 201
    And alp validates response body amd the token



