package stepDefinition.alparslan;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import testData.alp_testData;

import java.util.HashMap;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class US01_alp_getStatus_stepDefinitions {

    Response response;

    @When("alp User sends GET Request for status of API")
    public void alpUserSendsGETRequestForStatusOfAPI() {

        spec.pathParam("param", "status");
        response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .get("{param}");
        response.prettyPrint();
    }


    @Then("alp verifies status code is {int}")
    public void alpVerifiesStatusCodeIs(int statusCode) {
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(statusCode);
    }



    @And("alp validates response body")
    public void alpValidatesResponseBody() {

        response.then()
                .assertThat()
                .body(Matchers.containsString("status"));



        alp_testData expectedData = new alp_testData();

        HashMap actualData = convertJsonToJava(response.asString(), HashMap.class);

        MatcherAssert.assertThat(expectedData.status
                ,is(equalTo(actualData.get("status"))));
    }


}
