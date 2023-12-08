package stepDefinition.fatma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US02_TC01_TC02_TC03_Get_ListOfBooks {
    Response response;
    @Given("User send GET request to get the list of books")
    public void userSendGETRequestToGetTheListOfBooks() {
        spec.pathParam("first", "books");
        response = given(spec).get("{first}");
        response.prettyPrint();

    }

    @Then("Verify the response body consists of books {string}, {string}, {string}, {string}")
    public void verifyTheResponseBodyConsistsOfBooks(String id, String name, String type, String available) {
        response.then().statusCode(200).
                body( hasItems(greaterThan(0)));
        JsonPath jsonPath = response.jsonPath();
        int size = response.jsonPath().getList("findAll{it.id}.id").size();
        assertTrue(size>0);

        String actId = response.jsonPath().getList("findAll{it.id}.id").get(4).toString();
        String actName = jsonPath.getList("findAll{it.name}.name").get(4).toString();
//        String actAuthor = jsonPath.getList("findAll{it.name=='"+name+"'}.author").get(0).toString();
        String actType = jsonPath.getList("findAll{it.type}.type").get(4).toString();
//        String actPrice = jsonPath.getList("findAll{it.name=='"+name+"'}.price").get(0).toString();
//        String actCurrentStock = jsonPath.getList("findAll{it.name=='"+name+"'}.current-stock").get(0).toString();
        String actAvailable = jsonPath.getList("findAll{it.available}.available").get(4).toString();

        assertEquals(id,actId);
        assertEquals(name,actName);
       // assertEquals(author,actAuthor);
        assertEquals(type,actType);
//        assertEquals(price,actPrice);
//        assertEquals(currentstock,actCurrentStock);
        assertEquals(available,actAvailable);

//        String body = GetListOfBooksTestData.convertJsonToString(5,"Untamed","non-fiction",true);
//        BookListPojo expectedData = ObjectMapperUtils.convertJsonToJava(body, BookListPojo.class);
//        System.out.println("expectedData = " + expectedData);
//
//        BookListPojo  actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookListPojo.class);
//        System.out.println("actualData = " + actualData);
//
//        assertEquals(expectedData.getId(),actualData.getId());
//        assertEquals(expectedData.getName(),actualData.getName());
//        assertEquals(expectedData.getType(),actualData.getType());
//        assertEquals(expectedData.getAvailable(),actualData.getAvailable());


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
