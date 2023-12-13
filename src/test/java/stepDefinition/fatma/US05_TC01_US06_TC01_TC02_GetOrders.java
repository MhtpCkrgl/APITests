package stepDefinition.fatma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static stepDefinition.fatma.US04_TC01_TC02_TC03_PostAnOrder.*;

public class US05_TC01_US06_TC01_TC02_GetOrders {
        Response response;

        @And("User send GET request to get the ordered books")
        public void user_send_get_request_to_get_the_ordered_books() {
            spec.pathParam("first", "orders");
            response = given(spec).get("{first}");
            response.prettyPrint();
        }

        @Then("Verify the response body consists of the list of ordered books")
        public void verify_the_response_body_consists_of_the_list_of_ordered_books() {
//            response.then()
//                    .statusCode(200)
//                    .body("bookId", hasItems(4, 5),
//                            "customerName", hasItems("Damon Collins", "Sergio Considine", "Mrs. Hope Stoltenberg",
//                                    "Mrs. Byron Nolan", "Virginia Barrows", "Dora Walsh", "Fatma Rau", "Duane Howell"),
//                            hasSize(8), Matchers.containsString("1701719337733"));
        }

    @Given("User send GET request to get an ordered books using valid orderId")
    public void userSendGETRequestToGetAnOrderedBooksUsingValidOrderId() {
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Verify the response body consists of the information about the ordered book")
    public void verifyTheResponseBodyConsistsOfTheInformationAboutTheOrderedBook() {
        assertEquals(200,response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        assertEquals(orderId,jsonPath.getString("id"));
        assertEquals(customerName,jsonPath.getString("customerName"));
        assertEquals(bookId,jsonPath.getInt("bookId"));
        assertEquals(1,jsonPath.getInt("quantity"));
    }

    @Given("User send GET request to get an ordered books using invalid orderId")
    public void userSendGETRequestToGetAnOrderedBooksUsingInvalidOrderId() {
        spec.pathParams("first", "orders","second","abfytdrskouy");
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Verify the response body consists of the error message.")
    public void verifyTheResponseBodyConsistsOfTheErrorMessage() {
        response.then().statusCode(404).
                body(containsString("No order with id abfytdrskouy."))
                .body("error",equalTo("No order with id abfytdrskouy."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");
        assertEquals("No order with id abfytdrskouy.",errorMessage);

    }
}


