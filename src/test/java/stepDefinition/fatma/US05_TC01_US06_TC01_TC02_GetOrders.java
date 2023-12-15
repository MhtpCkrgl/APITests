package stepDefinition.fatma;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testData.SimpleBookTestData;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.codehaus.groovy.runtime.StringGroovyMethods.contains;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static stepDefinition.fatma.US04_TC01_TC02_TC03_PostAnOrder.*;

public class US05_TC01_US06_TC01_TC02_GetOrders {
        Response response;
        static int limit=6;

    @Given("User send POST request to order books")
    public void userSendPOSTRequestToOrderBooks() {
        for (int j = 0; j < limit; j++) {
            spec.pathParam("first", "orders");
            SimpleBookTestData sentBody = new SimpleBookTestData();
            Random random = new Random();
            Map<String, Object> expectedData;
            for (int i = 0; i < 7; i++) {
                bookId = random.nextInt(7);
                if (bookId == 2 || bookId == 0) {
                    continue;
                }
                expectedData = sentBody.orderBookRequestMethod(bookId, customerName);
                System.out.println(bookId);
                response = given(spec).body(expectedData).post("{first}");
                response.prettyPrint();
                break;
            }
        }
    }

        @And("User send GET request to get the ordered books")
        public void user_send_get_request_to_get_the_ordered_books() {
            spec.pathParam("first", "orders");
            response = given(spec).get("{first}");
            response.prettyPrint();
        }

        @Then("Verify the response body consists of the list of ordered books")
        public void verify_the_response_body_consists_of_the_list_of_ordered_books() {
            response.then().statusCode(200).body("id",hasSize(limit),
                    "quantity",equalTo(List.of(1,1,1,1,1,1)));
            JsonPath jsonPath = response.jsonPath();
            int size1 = jsonPath.getList("timestamp").size();
            assertEquals(limit,size1);
            int size2 = jsonPath.getList("quantity").size();
            assertEquals(limit,size2);

        }

    @Given("User send GET request to get an ordered books using valid orderId")
    public void userSendGETRequestToGetAnOrderedBooksUsingValidOrderId() {
        spec.pathParams("first", "orders","second",orderId);
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Verify the response body consists of the information about the ordered book")
    public void verifyTheResponseBodyConsistsOfTheInformationAboutTheOrderedBook() {
        assertEquals(200,response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        assertEquals(orderId,jsonPath.getString("id"));
        assertEquals(customerName,jsonPath.getString("customerName"));
        assertEquals(bookId,jsonPath.getInt("bookId"));
        assertEquals(1,jsonPath.getInt("quantity"));
    }

    @Given("User send GET request to get an ordered books using invalid orderId")
    public void userSendGETRequestToGetAnOrderedBooksUsingInvalidOrderId() {
        spec.pathParams("first", "orders","second","abfytdrskouy");
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Verify the response body consists of the error message.")
    public void verifyTheResponseBodyConsistsOfTheErrorMessage() {
        response.then().statusCode(404).
                body(containsString("No order with id abfytdrskouy."))
                .body("error",equalTo("No order with id abfytdrskouy."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");
        assertEquals("No order with id abfytdrskouy.",errorMessage);

    }


}


