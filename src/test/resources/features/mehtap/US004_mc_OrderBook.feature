Feature:
  @sdk
  Scenario:
    Given mc user sets url
    And mc user send POST request to order book
    Then mc user verify status code is 201
    Then mc user validates the response body
