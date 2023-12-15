Feature: US002 - Alp - GET list of books

  @WithoutToken
  Scenario: TC01_GET list of books
    When alp User sends GET Request to get list of books
    Then alp Verifies the status code is 200
    And alp User validates response body

  @WithoutToken
  Scenario: TC02_GET list of books using params
    When alp User sends GET Request with params to get list of books
    Then alp Verifies the status code is 200
    And alp User validates response bodyy


    #io.restassured.internal.http.HttpResponseException: status code: 400, reason phrase: Bad Request
  @WithoutToken @wip
  Scenario: TC03_GET List of books with invalid parameters
    When alp User sends GET Request to get list of books with wrong params
    Then alp Verifies the status code is 400
    And alp Validates the error message in the response