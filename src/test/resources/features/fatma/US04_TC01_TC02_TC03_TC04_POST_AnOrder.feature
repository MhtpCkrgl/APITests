Feature:
@RunWithToken @PostAnOrder
  Scenario:
    Given User send POST request to order a book
    Then User verify status code is 201 and validate the response body
    And User send GET request to get an ordered books using valid orderId
    Then Verify the response body consists of the information about the ordered book
    And User send PATCH request to update the ordered book using valid orderId
    Then Assert the response body is empty
    And User send GET request to get updated ordered book using valid orderId
    Then Assert the response body has been updated
    And User send DELETE request to delete the order
    Then Assert the order deleted successfully
    And User send GET request to get deleted order
    Then Verify the response body contains error message

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