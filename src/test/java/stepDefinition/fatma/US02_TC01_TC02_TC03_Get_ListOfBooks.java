package stepDefinition.fatma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testData.GetBookTestData;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US02_TC01_TC02_TC03_Get_ListOfBooks {
    Response response;
    Map<String, Object> expectedDataMap;
    @Given("User send GET request to get the list of books")
    public void userSendGETRequestToGetTheListOfBooks() {
        spec.pathParam("first", "books");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the response body consists of books {string}, {string}, {string}, {string}")
    public void verifyTheResponseBodyConsistsOfBooks(String id, String name, String type, String available) {
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        List<Object> list = jsonPath.getList("");
        assertTrue(list.size() > 0);

        List<String> idList = response.jsonPath().getList("findAll{it.id}.id");
        List<String> nameList = response.jsonPath().getList("findAll{it.name}.name");
        List<String> typeList = response.jsonPath().getList("findAll{it.type}.type");
        List<String> availableList = response.jsonPath().getList("findAll{it.available}.available");

        assertEquals(id, String.valueOf(idList.get(0)));
        assertEquals(name, String.valueOf(nameList.get(0)));
        assertEquals(type, String.valueOf(typeList.get(0)));
        assertEquals(available, String.valueOf(availableList.get(0)));

        String response2 = jsonPath.getString("type").replaceAll("non-fiction","nonfiction");

        String body = GetBookTestData.convertJsonToString_2("3","The Vanishing Half","fiction","true");
        BookPOJO expectedData = ObjectMapperUtils.convertJsonToJava(body, BookPOJO.class);
        BookPOJO  actualData = ObjectMapperUtils.convertJsonToJava(response2, BookPOJO.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getType(),actualData.getType());
        assertEquals(expectedData.isAvailable(),actualData.isAvailable());
    }
    @Given("User send GET request to get list of books \\(using valid parameters)")
    public void userSendGETRequestToGetListOfBooksUsingValidParameters() {
        spec.pathParam("first", "books").
                queryParams("type","non-fiction","limit","4");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the response body returns at least one data")
    public void verifyTheResponseBodyReturnsAtLeastOneData() {
        response.then().statusCode(200).
                 body(containsString("non-fiction")) ;

        JsonPath jsonPath = response.jsonPath();
        int size = response.jsonPath().getList("findAll{it.type}.type").size();
        assertTrue(size>0);
    }

    @Given("User send GET request to get list of books \\(using invalid parameters)")
    public void userSendGETRequestToGetListOfBooksUsingInvalidParameters() {
        spec.pathParam("first", "books").
                queryParams("type","novel","limit","12");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the response body returns an error message")
    public void verifyTheResponseBodyReturnsAnErrorMessage() {
        response.then().statusCode(400).
                body(containsString("Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction."))
                        .body("error",equalTo("Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction."));

        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");;
        assertEquals("Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction.",errorMessage);
    }
}
