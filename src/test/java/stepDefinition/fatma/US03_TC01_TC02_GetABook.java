package stepDefinition.fatma;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testData.GetBookTestData;
import utilities.ObjectMapperUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class US03_TC01_TC02_GetABook {
    Response response;

    @Given("User send GET request to get a book \\(using valid bookId)")
    public void user_send_get_request_to_get_a_book_using_valid_book_id() {

            spec.pathParams("first", "books","second",4);

            response = given(spec).get("{first}/{second}");
            response.prettyPrint();
    }



    @Then("Verify the response body consists of a book {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void verifyTheResponseBodyConsistsOfABook(String id, String name, String author, String type, String price, String currentStock, String available) {
        response.then().statusCode(200).
                body("id", equalTo(4),
                        "name",equalTo("The Midnight Library"),
                        "author",equalTo("Matt Haig"),
                        "type",equalTo("fiction"),
                        "price",equalTo(15.6F),
                        "current-stock",equalTo(87),
                        "available",equalTo(true));
        JsonPath jsonPath = response.jsonPath();

        String actId = jsonPath.getString("id");
        String actName = jsonPath.getString("name");
        String actAuthor = jsonPath.getString("author");
        String actType = jsonPath.getString("type");
        String actPrice = jsonPath.getString("price");
        String actCurrentStock = jsonPath.getString("current-stock");
        String actAvailable = jsonPath.getString("available");

        assertEquals(id,actId);
        assertEquals(name,actName);
         assertEquals(author,actAuthor);
        assertEquals(type,actType);
        assertEquals(price,actPrice);
        assertEquals(currentStock,actCurrentStock);
        assertEquals(available,actAvailable);

        String response2 = response.asString().replaceAll("current-stock","currentStock");
        String body = GetBookTestData.convertJsonToString(id,name,author,type,price,currentStock,available);
       BookPOJO expectedData = ObjectMapperUtils.convertJsonToJava(body, BookPOJO.class);
      // BookPOJO expectedData = new BookPOJO(Integer.parseInt(id),name,author,type,price,currentStock,Boolean.parseBoolean(available));
        System.out.println("expectedData = " + expectedData);

       BookPOJO  actualData = ObjectMapperUtils.convertJsonToJava(response2, BookPOJO.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getAuthor(),actualData.getAuthor());
        assertEquals(expectedData.getType(),actualData.getType());
        assertEquals(Double.parseDouble(price),actualData.getPrice());
        assertEquals(currentStock,actualData.getCurrentStock());
        assertEquals(Boolean.valueOf(available),actualData.isAvailable());
    }


    @Given("User send GET request to get a book \\(using invalid bookId)")
    public void userSendGETRequestToGetABookUsingInvalidBookId() {
        spec.pathParams("first", "books","second",0);

        response = given(spec).get("{first}/{second}");
        response.prettyPrint();
    }

    @Then("Assert the response body returns an error message")
    public void assertTheResponseBodyReturnsAnErrorMessage() {
        response.then().statusCode(400).
                body(containsString("No book with id 0"))
                .body("error",equalTo("No book with id 0"));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");;
        assertEquals("No book with id 0",errorMessage);

    }
}


