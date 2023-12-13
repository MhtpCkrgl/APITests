package stepDefinition.fatma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinition.fatma.US04_TC01_TC02_TC03_PostAnOrder.orderId;

public class US08_DELETE_AnOrder {
    Response response;
    @And("User send DELETE request to delete the order")
    public void user_send_delete_request_to_delete_the_order() {
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).delete("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Assert the order deleted successfully")
    public void assert_the_order_deleted_successfully() {
        assertEquals(204,response.statusCode());
        assertEquals("",response.asString());
        assertTrue(response.asString().isEmpty());
    }

    @And("User send GET request to get deleted order")
    public void userSendGETRequestToGetDeletedOrder() {
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();

    }

    @Then("Verify the response body contains error message")
    public void verifyTheResponseBodyContainsErrorMessage() {
        assertEquals(404,response.statusCode());
        response.then().body(containsString("No order with id "+orderId+"."))
                .body("error",equalTo("No order with id "+orderId+"."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");
        assertEquals("No order with id "+orderId+".",errorMessage);
    }
}