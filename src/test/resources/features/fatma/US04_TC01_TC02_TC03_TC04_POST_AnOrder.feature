Feature:
@RunWithToken @PostAnOrder
  Scenario:
    Given User send POST request to order a book
    Then User verify status code is 201 and validate the response body

@WithoutToken @PostAnOrder
  Scenario:
    Given User send POST request to order a book (without token)
    Then Assert status code is 401 and validate the response body


@RunWithToken @PostAnOrderWithInvalidBookId
  Scenario:
    Given User send POST request to order a book (with an invalid bookId)
    Then User verify status code is 400 and validate the response body contains error message

  @RunWithToken @PostAnOrderWithOutOfStockBookId
  Scenario:
    Given User send POST request to order a book (using out of stock bookId)
    Then Assert the status code is 404 and validate the response body contains error message