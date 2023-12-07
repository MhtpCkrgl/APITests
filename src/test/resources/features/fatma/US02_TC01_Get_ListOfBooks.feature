@RunWithToken @bookList
Feature:

  Scenario Outline:GET List of Books
    Given User send GET request to get the list of books
    Then Verify the response body consists of books "<id>", "<name>", "<type>", "<available>"
    Examples:
      | id   | name   | type     | available |
      |  5   | Untamed| non-fiction | true     |
