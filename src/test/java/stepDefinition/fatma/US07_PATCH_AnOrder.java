package stepDefinition.fatma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinition.fatma.US04_TC01_TC02_TC03_PostAnOrder.*;

public class US07_PATCH_AnOrder {
    Response response;
    @And("User send PATCH request to update the ordered book using valid orderId")
    public void user_send_patch_request_to_update_the_ordered_book_using_valid_order_id() {
        String body = "{\n" +
                "  \"customerName\": \"Fatma Tarim\"\n" +
                "}";
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).body(body).patch("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Assert the response body is empty")
    public void assertTheResponseBodyIsEmpty() {
        assertEquals("", response.asString());
        assertTrue(response.asString().isEmpty());
    }

    @And("User send GET request to get updated ordered book using valid orderId")
    public void userSendGETRequestToGetUpdatedOrderedBookUsingValidOrderId() {
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Assert the response body has been updated")
    public void assertTheResponseBodyHasBeenUpdated() {
        assertEquals(200,response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        assertEquals(orderId,jsonPath.getString("id"));
        assertEquals("Fatma Tarim",jsonPath.getString("customerName"));
        assertEquals(bookId,jsonPath.getInt("bookId"));
        assertEquals(1,jsonPath.getInt("quantity"));
    }
}
