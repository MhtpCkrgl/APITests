package stepDefinition.fatma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class US01_TC01_GET_Status {
    Response response;

    @Given("User send GET request for the status code")
    public void user_send_get_request_for_the_status_code() {
        spec.pathParam("first", "status");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the status code equals to {int}")
    public void verify_the_status_code_equals_to(Integer statusCode) {
        response.then().statusCode(statusCode).
                body(containsString("OK")).
                body("status", equalTo("OK")).
                contentType("application/json; charset=utf-8");
        JsonPath status = response.jsonPath();
        assertEquals("OK",status.getString("status"));

    }
}