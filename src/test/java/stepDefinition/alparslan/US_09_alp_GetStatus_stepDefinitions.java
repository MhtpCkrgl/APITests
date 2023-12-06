package stepDefinition.alparslan;


import com.github.javafaker.Faker;


import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigReader;


import java.util.HashMap;
import java.util.Map;


import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static utilities.Authentication.fakerMail;
import static utilities.Authentication.fakerName;


public class US_09_alp_GetStatus_stepDefinitions {

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


        requestPayload = new HashMap<>();
        requestPayload.put("clientName", clientName);
        requestPayload.put("clientEmail", clientEmail);


        spec.pathParam("param", "api-clients");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post("{param}");
        response.prettyPrint();


    }


    @Then("alp Verifies status code is {int}")
    public void alpVerifiesStatusCodeIs(int arg0) {


    }

    @And("alp validates response body amd the token")
    public void alpValidatesResponseBodyAmdTheToken() {
    }


}
