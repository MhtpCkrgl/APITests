@RunWithToken @bookList
Feature:

  Scenario Outline:GET List of Books
    Given User send GET request to get the list of books
    Then Verify the response body consists of books "<id>", "<name>", "<type>", "<available>"
    Examples:
      | id   | name   | type     | available |
      |  5   | Untamed| non-fiction | true     |

  @RunWithToken @bookListwithParams
Scenario:
  Given User send GET request to get list of books (using valid parameters)
  Then Verify the response body returns at least one data

  @RunWithToken @bookListwithInvalidParams
  Scenario:
    Given User send GET request to get list of books (using invalid parameters)
    Then Verify the response body returns an error message
