package stepDefinition.sırma;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetStatus {
    Response response;
    JsonPath json;
    @Given("Apinin status kodu icin Get request hazirligi yapilir")
    public void apininStatusKoduIcinGetRequestHazirligiYapilir() {
        spec.pathParam("first", "status");
    }
    @When("Apinin status kodu icin Get request gönderilir")
    public void apininStatusKoduIcinGetRequestGönderilir() {
        response = given(spec).get("{first}");
        //response.prettyPrint();
    }

    @Then("Response un status kodunun {int} olduğu doğrulanır")
    public void responseUnStatusKodununOlduğuDoğrulanır(int statusCode) {
        response.then().statusCode(statusCode).
                body(containsString("OK")).
                body("status",equalTo("OK")).
                contentType("application/json; charset=utf-8");
    }


}
