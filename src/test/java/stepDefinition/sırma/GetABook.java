package stepDefinition.sırma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetABook {
    Response response;
    JsonPath json;

    @Given("Kullanici listeden kitap seçmek için request hazirligi yapar {string}")
    public void kullaniciListedenKitapSeçmekIçinRequestHazirligiYapar(String id) {
        spec.pathParams("first", "books","second",id);
    }
    @When("Kullanici listeden kitap seçmek için request gonderir")
    public void kullaniciListedenKitapSeçmekIçinRequestGonderir() {
        response = given(spec).when().get("{first}/{second}");

    }
    @Then("status kodun {int} oldugunu dogrular")
    public void statusKodunOldugunuDogrular(int statusCode) {
        assertEquals(response.statusCode(),statusCode);

    }

    @And("Kitabın bilgilerinin dogru oldugunu dogrular {string}, {string}, {string},{string},{string},{string},{string},")
    public void kitabınBilgilerininDogruOldugunuDogrular(String id, String name, String author, String type, String price, String current, String available) {
        assertEquals(response.jsonPath().getString("name"),name);
        assertEquals(response.jsonPath().getString("id"),id);
        assertEquals(response.jsonPath().getString("author"),author);
        assertEquals(response.jsonPath().getString("type"),type);
        assertEquals(response.jsonPath().getString("price"),price);
        assertEquals(response.jsonPath().getString("current-stock"),current);
        assertEquals(response.jsonPath().getString("available"),available);


    }




}
