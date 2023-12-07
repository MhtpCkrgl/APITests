package stepDefinition.bengu;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class US001_bengu_Status_stepDef {
    Response response;

    @Given("Bengu-Api durumunu sorgulamak icin bir Get request gonder")
    public void benguApiDurumunuSorgulamakIcinBirGetRequestGonder() {
        spec.pathParam("1", "status");
        response = given(spec).when().get("{1}");

    }

    @Then("Bengu-Status Code {int} oldugunu dogrula")
    public void benguStatusCodeOldugunuDogrula(int code) {
        response.then().statusCode(code);
    }

    @And("Bengu-Responstan {string} key dogrulamasi yap")
    public void benguResponstanKeyDogrulamasiYap(String key) {
        response.then().body(key, equalTo("OK"));
    }

}
