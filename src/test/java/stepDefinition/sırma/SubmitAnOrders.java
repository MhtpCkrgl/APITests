package stepDefinition.sırma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static base_url.BaseUrl.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ObjectMapperUtils.convertJsonToJava;


public class SubmitAnOrders {
    Response response;
    @Given("Siparis olusturmak icin request hazirligi yapar")
    public void siparisOlusturmakIcinRequestHazirligiYapar() {
        spec.pathParam("first", "orders");
    }
    @When("Siparis olusturmak icin  request gonderir")
    public void siparisOlusturmakIcinRequestGonderir() {

        Map<String,String> bookOrders=new HashMap<>();
        bookOrders.put("bookId","5");
        bookOrders.put("customerName","mark");
        response =given(spec).when().body(bookOrders).post("{first}");
        response.prettyPrint();
    }

    @And("Siparis olusturuldugu dogrulanir")
    public void siparişOlusturulduguDogrulanir() {
        HashMap actualData = convertJsonToJava(response.asString(),HashMap.class);
        assertEquals(actualData.get("created"), true);
        assertEquals(201, response.statusCode());
    }


}
