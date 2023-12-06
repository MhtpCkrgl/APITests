Feature: US004 -  Submit an order

  @WithoutToken
  Scenario: TC01_POST Submit an order
    Given alp User generates token
    When alp User sends POST Request to order a book
    Then alp Verifiess status code is 201
    And alp Validates the response body



