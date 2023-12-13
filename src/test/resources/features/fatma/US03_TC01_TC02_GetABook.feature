Feature:
  @RunWithToken @bookWithAValidBookId
Scenario Outline:
Given User send GET request to get a book (using valid bookId)
Then Verify the response body consists of a book "<id>", "<name>", "<author>", "<type>", "<price>", "<current-stock>", "<available>"
  Examples:
    | id   | name                | author    | type       | price | current-stock| available |
    |  4   | The Midnight Library| Matt Haig |fiction     | 15.6  |87            |true     |

  @RunWithToken @bookWithInvalidBookId
  Scenario:
    Given User send GET request to get a book (using invalid bookId)
    Then Assert the response body returns an error message