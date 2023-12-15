package stepDefinition.alparslan;


import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import testData.alp_testData;

import java.util.HashMap;
import java.util.Map;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.ObjectMapperUtils.convertJsonToJava;


public class US09_alp_ApiAuthentication_stepDefinitions {

    Response response;
    Faker faker;

    Map<String, Object> requestPayload;
    String clientName;
    String clientEmail;

    @When("alp User sends POST request to register and generate token")
    public void alpUserSendsPOSTRequestToRegisterAndGenerateToken() {

        faker = new Faker();
        clientName = faker.name().firstName();
        clientEmail = faker.internet().emailAddress();

        // System.out.println("clientName1  " + clientName);
        // System.out.println("clientEmail1   " + clientEmail);

        requestPayload = new HashMap<>();
        requestPayload.put("clientName", clientName);
        requestPayload.put("clientEmail", clientEmail);

        spec.pathParam("param", "api-clients");
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post("{param}");
        response.prettyPrint();
    }


    @Then("alp Verifies status code is {int}")
    public void alpVerifiesStatusCodeIs(int statusCode) {
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(statusCode);
    }

    @And("alp validates response body and the token")
    public void alpValidatesResponseBodyAndTheToken() {
        response.then()
                .assertThat()
                .body(containsString("accessToken"));
        response = null;
    }


    @When("alp User creates a new API client to have registered credentials")
    public void alpUserCreatesANewAPIClientToHaveRegisteredCredentials() {

        faker = new Faker();
        clientName = faker.name().firstName();
        clientEmail = faker.internet().emailAddress();

        //  System.out.println("clientName 2  " +clientName);
        //  System.out.println("clientEmail 2  " + clientEmail);

        requestPayload = new HashMap<>();
        requestPayload.put("clientName", clientName);
        requestPayload.put("clientEmail", clientEmail);

        // System.out.println("clientName 2.1  " +clientName);
        // System.out.println("clientEmail 2.1" + clientEmail);

        spec.pathParam("param", "api-clients");
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post("{param}");
        //  response.prettyPrint();

    }

    @And("alp User sends POST request to register with registered credentials")
    public void alpUserSendsPOSTRequestToRegisterWithRegisteredCredentials() {

        spec.pathParam("param", "api-clients");
        response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post("{param}");
        //  response.prettyPrint();
    }

    @And("alp validates response payload has error message")
    public void alpValidatesResponsePayloadHasErrorMessage() {
        response.then()
                .assertThat()
                .body(Matchers.containsString("error"));


        alp_testData expectedData = new alp_testData();

        HashMap actualData = convertJsonToJava(response.asString(), HashMap.class);

        MatcherAssert.assertThat(expectedData.registeredClientCredentials_ErrorMsg
                , is(equalTo(actualData.get("error"))));


    }
}
