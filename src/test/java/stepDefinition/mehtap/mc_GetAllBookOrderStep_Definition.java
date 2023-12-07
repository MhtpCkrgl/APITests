package stepDefinition.mehtap;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import testData.SimpleBookTestData;
import utilities.ReusableMethods;

import java.util.Map;
import java.util.Random;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static stepDefinition.mehtap.mehtap.bookId;
import static stepDefinition.mehtap.mehtap.customerName;

public class mc_GetAllBookOrderStep_Definition {

    Response response;

    @And("mc user send GET request to all ordered book")
    public void mcUserSendGETRequestToAllOrderedBook() {
        // set the expected data
        SimpleBookTestData obj = new SimpleBookTestData();

        Map<String, Object> expectedData = obj.orderBookRequestMethod(bookId, customerName);

        // send the request and get the response
        response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

    }
}
