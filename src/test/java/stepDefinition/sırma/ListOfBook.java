package stepDefinition.sırma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class ListOfBook {
    Response response;
    JsonPath json;
    List<String> book;

    @Given("Kitaplari goruntuleyebilmek icin Get request hazirligi yapar")
    public void kitaplariGoruntuleyebilmekIcinGetRequestHazirligiYapar() {
        spec.pathParam("first", "books");
    }

    @When("Kitaplari goruntuleyebilmek icin Get request gönderir")
    public void kitaplariGoruntuleyebilmekIcinGetRequestGönderir() {
        response = given(spec).when().get("{first}");
        assertEquals(response.statusCode(),200);
    }
    @And("Listede  kitap oldugunu dogrular")
    public void listedeKitapOldugunuDogrular() {
        json=response.jsonPath();
        book=json.getList("findAll{it.id > 0}.name");
        //[The Russian, Just as I Am, The Vanishing Half, The Midnight Library, Untamed, Viscount Who Loved Me]
        Assert.assertTrue( book.size()>0);
    }
    @Then("Kitaplarin isimlerinin {string} oldugunu dogrular")
    public void KitaplarinIsimlerininOldugunuDogrular(String Kitaplar) {
        assertTrue(book.toString().contains(Kitaplar));

    }
    
    @And("Stokta olmayan kitabın {string} oldugunu dogrular")
    public void stoktaOlmayanKitabınOldugunuDogrular(String stoktaOlmayanKitap) {
        String unavailableBook=json.getList("findAll{it.available==false}.name").toString();
        assertTrue(unavailableBook.contains(stoktaOlmayanKitap));


    }


}
