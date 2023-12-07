package stepDefinition.mehtap;

import base_url.BaseUrl;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import testData.SimpleBookTestData;
import utilities.ReusableMethods;

import java.util.HashMap;
import java.util.Map;

import static base_url.BaseUrl.setUpWithAuth;
import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class mehtap extends BaseUrl {
    static int bookId;
    Faker fakerName;
    Response response;
    static String customerName;

    @Given("mc user sets url")
    public void mcUserGoesUrl() {
        //set the url
        setUpWithAuth();
        spec.pathParam("first", "orders");

    }

    @Given("mc user sets url but dont take authentication")
    public void mcUserSetsUrlButDontTakeAuthentication() {
        //set the url
        setUp();
        spec.pathParam("first", "orders");
    }

    @And("mc user send POST request to order book")
    public void mcUserSendPOSTRequestToOrderBook() {
        // set the expected data
        SimpleBookTestData obj = new SimpleBookTestData();
        bookId = ReusableMethods.generateRandomNumber();
        fakerName = new Faker();
        customerName = fakerName.name().fullName();
        Map<String, Object> expectedData = obj.orderBookRequestMethod(bookId, customerName);


        // send the request and get the response
        response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();
    }

    @Then("mc user verify status code is {int}")
    public void mcUserVerifyStatusCodeIs(int arg0) {
        assertEquals(201, response.statusCode());
    }

    @Then("mc user validates the response body")
    public void mcUserValidatesTheResponseBody() {
        //do assertion
        HashMap actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(actualData.get("created"), true);
        //orderId icin dogrulama yaopmak biraz sikinti cunku her seferinde farkli bir data dondurecek
        //assertTrue(response.body("orderId",hasSize(1)));
        assertEquals(201, response.statusCode());
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        //response time dogrulamasi yapilabilir burada


    }


}
