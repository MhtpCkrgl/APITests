package stepDefinition.bengu;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


public class US009_bengu_Authentication_stepDef {
    Map<String, Object> payload;
    Response response;
    Faker faker;
    String name;
    String email;

    @When("Bengu-Register icin url duzenle ve POST request gonder")
    public void benguRegisterIcinUrlDuzenleVePOSTRequestGonder() {
        //    "accessToken": "1956bc7025055a429a5591254ee01849ded55b54e3d568de1a5cfc8679b53ff0"
        faker=new Faker();
        name=faker.name().firstName();
        email=faker.internet().emailAddress();
        payload = new HashMap<>();

        spec.pathParam("1", "api-clients");
        payload.put("clientName", name);
        payload.put("clientEmail", email);

        response = given(spec).body(payload).when().post("{1}");

    }

    @And("Bengu-StatusCodeName created ve responsun accesToken icerdigini dogrula")
    public void benguStatusCodeNameCreatedVeResponsunAccesTokenIcerdiginiDogrula() {
        response.then()
                .statusCode(201)
                .statusLine("HTTP/1.1 201 Created")
                .body(containsString("accessToken"));
    }

    @Then("Bengu-Status Code {int} oldugunu dogrula \\(auth)")
    public void benguStatusCodeOldugunuDogrulaAuth(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
