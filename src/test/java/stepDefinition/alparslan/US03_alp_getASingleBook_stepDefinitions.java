package stepDefinition.alparslan;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;

public class US03_alp_getASingleBook_stepDefinitions {


    Response response;

    @When("alp User sends GET Request to get a single book")
    public void alpUserSendsGETRequestToGetASingleBook() {
//    spec.pathParam()
//    response = given().spec(spec).when().get()

    }

    @Then("alp Verifies status code iss {int}")
    public void alpVerifiesStatusCodeIss(int arg0) {
    }

    @And("alp User gets a single book and validates")
    public void alpUserGetsASingleBookAndValidates() {
    }

    @And("alp Validates response body")
    public void alpValidatesResponseBody() {
    }
}
