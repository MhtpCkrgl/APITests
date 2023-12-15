Feature: US03 - Alp - GET a single book

  @WithoutToken @wip
  Scenario: TC01_Get a single book
    When alp User sends GET Request to get a single book
    Then alp Verifies status code iss 200
    And alp User gets a single book and validates


  @WithoutToken
  Scenario: TC02_Get a single book with invalid book Id
    When alp User sends GET Request to get a single book with invalid id
    Then alp Verifies status code iss 404
    And alp Validates response body