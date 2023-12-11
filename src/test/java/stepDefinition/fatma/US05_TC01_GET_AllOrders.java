package stepDefinition.fatma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class US05_TC01_GET_AllOrders {
    Response response;

    @Given("User send GET request to get the ordered books")
    public void user_send_get_request_to_get_the_ordered_books() {
        spec.pathParam("first", "orders");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the response body consists of the list of ordered books")
    public void verify_the_response_body_consists_of_the_list_of_ordered_books() {
        response.then()
                .statusCode(200)
                .body("bookId",hasItems(4,5),
                "customerName",hasItems("Damon Collins", "Sergio Considine", "Mrs. Hope Stoltenberg",
                "Mrs. Byron Nolan", "Virginia Barrows","Dora Walsh","Fatma Rau","Duane Howell"),
                        hasSize(8),containsString("1701719337733"));

    }
}