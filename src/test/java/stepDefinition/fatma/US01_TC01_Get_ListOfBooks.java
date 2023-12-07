package stepDefinition.fatma;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class US01_TC01_Get_ListOfBooks {
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
                body("id", hasSize(greaterThan(0)));
        JsonPath jsonPath = response.jsonPath();
        int size = response.jsonPath().getList("findAll{it.id}.id").size();
        assertTrue(size>0);

        //Object actId = response.jsonPath().getList("findAll{it.name=='"+name+"'}.id").get(4);
        String actName = jsonPath.getList("findAll{it.name=='"+name+"'}.name").get(4).toString();
//        String actAuthor = jsonPath.getList("findAll{it.name=='"+name+"'}.author").get(0).toString();
        String actType = jsonPath.getList("findAll{it.name=='"+name+"'}.type").get(4).toString();
//        String actPrice = jsonPath.getList("findAll{it.name=='"+name+"'}.price").get(0).toString();
//        String actCurrentStock = jsonPath.getList("findAll{it.name=='"+name+"'}.current-stock").get(0).toString();
        String actAvailable = jsonPath.getList("findAll{it.name=='"+name+"'}.available").get(4).toString();


       // assertEquals(id,actId);
        assertEquals(name,actName);
       // assertEquals(author,actAuthor);
        assertEquals(type,actType);
//        assertEquals(price,actPrice);
//        assertEquals(currentstock,actCurrentStock);
        assertEquals(available,actAvailable);
        String body = "{\n" +
                "                                       \"id\": 5,\n" +
                "                                       \"name\": \"Untamed\",\n" +
                "                                       \"type\": \"non-fiction\",\n" +
                "                                       \"available\": true\n" +
                "                                     }";
        Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(body, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        Map<String,Object>  actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("type"),actualData.get("type"));
        assertEquals(expectedData.get("available"),actualData.get("available"));


    }
}
