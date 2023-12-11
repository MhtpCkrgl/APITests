package stepDefinition.mehtap;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static base_url.BaseUrl.setUpWithAuth;
import static base_url.BaseUrl.spec;

public class mc_GetSingleBookById_stepDefinitions {
    @And("mc user send GET request to get single book with id")
    public void mcUserSendGETRequestToGetSingleBookWithId() {


    /*
       //set the expected Data
        pojodan obje olusturulabilir ya da methoddN


        //send request and get the response
        Response response = given(spec).body().when().get("{first}/{second}");
        response.prettyPrint();

        //do assertion

        ResponsePOJO actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), ResponsePOJO.class);
        assertEquals(201,response.statusCode());
        assertEquals("The Russian",actualData.userId());
     */
    }

    @Given("mc user sets url for getSingleBookById")
    public void mcUserSetsUrlForGetSingleBookById() {

        //set the url
        setUpWithAuth();
       // spec.pathParam("first", "orders","second","bookId");

    }
}
