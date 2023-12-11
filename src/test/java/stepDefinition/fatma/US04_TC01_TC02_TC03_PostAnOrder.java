package stepDefinition.fatma;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testData.SimpleBookTestData;
import utilities.ReusableMethods;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static java.lang.Math.random;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class US04_TC01_TC02_TC03_PostAnOrder {

    static int bookId;
    static Faker fakerName= new Faker();
    Response response;
    static String customerName= fakerName.name().fullName();
    public static String orderId;

    @Given("User send POST request to order a book")
    public void user_send_post_request_to_order_a_book() {
        spec.pathParam("first", "orders");

    }

    @Then("User verify status code is {int} and validate the response body")
    public void user_verify_status_code_is_and_validate_the_response_body(int statuscode) {

        SimpleBookTestData sentBody = new SimpleBookTestData();
        Random random = new Random();
        boolean sart = bookId != 0 && bookId != 2;
        do {
            bookId = random.nextInt(7);
            if (!sart) {
                continue;
            }
        } while (!sart);


        Map<String, Object> expectedData = sentBody.orderBookRequestMethod(bookId, customerName);
        System.out.println(bookId);
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        assertEquals(statuscode, response.statusCode());
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        HashMap actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(true, actualData.get("created"));

        orderId = response.jsonPath().getString("orderId");
    }


    @Given("User send POST request to order a book \\(without token)")
    public void userSendPOSTRequestToOrderABookWithoutToken() {

        SimpleBookTestData sentBody = new SimpleBookTestData();
        Random random = new Random();
        boolean sart = bookId != 0 && bookId != 2;
        do{
            bookId = random.nextInt(7);
        }while(sart);

            Map<String, Object> expectedData = sentBody.orderBookRequestMethod(bookId, customerName);
            System.out.println(bookId);


        spec.pathParam("first", "orders");
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
    }
    @Then("Assert status code is {int} and validate the response body")
    public void assertStatusCodeIsAndValidateTheResponseBody(int durumkodu) {
        assertEquals(durumkodu, response.statusCode());
        response.then().statusCode(durumkodu).
                body(containsString("Missing Authorization header."))
                .body("error",equalTo("Missing Authorization header."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");;
        assertEquals("Missing Authorization header.",errorMessage);

    }

    @Given("User send POST request to order a book \\(with an invalid bookId)")
    public void userSendPOSTRequestToOrderABookWithAnInvalidBookId() {
        SimpleBookTestData sentBody = new SimpleBookTestData();
        Map<String, Object> expectedData = sentBody.orderBookRequestMethod(0, customerName);
        spec.pathParam("first", "orders");
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

    }

    @Then("User verify status code is {int} and validate the response body contains error message")
    public void userVerifyStatusCodeIsAndValidateTheResponseBodyContainsErrorMessage(int durumCode) {
        assertEquals(durumCode, response.statusCode());
        response.then().statusCode(durumCode).
                body(containsString("Invalid or missing bookId."))
                .body("error",equalTo("Invalid or missing bookId."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");;
        assertEquals("Invalid or missing bookId.",errorMessage);
    }

    @Given("User send POST request to order a book \\(using out of stock bookId)")
    public void userSendPOSTRequestToOrderABookUsingOutOfStockBookId() {
        SimpleBookTestData sentBody = new SimpleBookTestData();
        Map<String, Object> expectedData = sentBody.orderBookRequestMethod(2, customerName);
        spec.pathParam("first", "orders");
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

    }

    @Then("Assert the status code is {int} and validate the response body contains error message")
    public void assertTheStatusCodeIsAndValidateTheResponseBodyContainsErrorMessage(int code) {
        assertEquals(code, response.statusCode());
        response.then().statusCode(code).
                body(containsString("This book is not in stock. Try again later."))
                .body("error",equalTo("This book is not in stock. Try again later."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");;
        assertEquals("This book is not in stock. Try again later.",errorMessage);
    }
}

