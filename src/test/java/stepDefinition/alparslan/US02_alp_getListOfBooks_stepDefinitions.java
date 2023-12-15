package stepDefinition.alparslan;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.client.HttpResponseException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import testData.alp_testData;

import java.util.HashMap;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utilities.ObjectMapperUtils.convertJsonToJava;


public class US02_alp_getListOfBooks_stepDefinitions {

    Response response;
    HashMap availableBookData;

    static int alp_availableBookId;
    static int alp_unAvailableBookId;

    @When("alp User sends GET Request to get list of books")
    public void alpUserSendsGETRequestToGetListOfBooks() {
        // set the url and request body
        spec.pathParam("param", "books");
        response = given().spec(spec)
                .when()
                .get("{param}");
        response.prettyPrint();


    }

    @Then("alp Verifies the status code is {int}")
    public void alpVerifiesTheStatusCodeIs(int statusCode) {

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(statusCode);
    }

    @And("alp User validates response body")
    public void alpUserValidatesResponseBody() {
        // do assertion
        alp_testData testData = new alp_testData();
        JSONArray expectedData = testData.expectedListOfBooks();

        JSONArray responseBody = new JSONArray(response.asString());

//        System.out.println("expectedData.get(0) = " + expectedData.get(0));
//        System.out.println("responseBody.get(0) = " + responseBody.get(0));

        for (int i = 0; i < responseBody.length(); i++) {
            MatcherAssert.assertThat(responseBody.get(i).toString()
                    , is(equalTo(expectedData.get(i).toString())));
        }
    }


    @When("alp User sends GET Request with params to get list of books")
    public void alpUserSendsGETRequestWithParamsToGetListOfBooks() {

        // set the url and request body
        spec.pathParam("param", "books")
                .queryParams("type", "non-fiction", "limit", "2");
        response = given().spec(spec)
                .when()
                .get("{param}");
        response.prettyPrint();


    }


    @And("alp User validates response bodyy")
    public void alpUserValidatesResponseBodyy() {

        alp_testData testData = new alp_testData();
        JSONArray expectedData = testData.expectedListOfBooksAfterUsingParams();

        JSONArray responseBody = new JSONArray(response.asString());

//        System.out.println("expectedData.get(0) = " + expectedData.get(0));
//        System.out.println("responseBody.get(0) = " + responseBody.get(0));

        for (int i = 0; i < responseBody.length(); i++) {
            MatcherAssert.assertThat(responseBody.get(i).toString()
                    , is(equalTo(expectedData.get(i).toString())));

            availableBookData = convertJsonToJava(responseBody.get(i).toString(), HashMap.class);
            if (availableBookData.get("available").toString().equals("true")) {
                alp_availableBookId = Integer.parseInt(availableBookData.get("id").toString());
            } else alp_unAvailableBookId = Integer.parseInt(availableBookData.get("id").toString());
        }

//        System.out.println("alp_availableBookId = " + alp_availableBookId);
//        System.out.println("alp_unAvailableBookId = " + alp_unAvailableBookId);


    }

    @When("alp User sends GET Request to get list of books with wrong params")
    public void alpUserSendsGETRequestToGetListOfBooksWithWrongParams() {
        // set the url and request body
        spec.pathParam("param", "books")
                .queryParams("type", "crime", "limit", "12");

        response = given().spec(spec)
                .when()
                .get("{param}");
        response.prettyPrint();
    }

    @And("alp Validates the error message in the response")
    public void alpValidatesTheErrorMessageInTheResponse() {

        response.then()
                .assertThat()
                .body(Matchers.containsString("error"));


        alp_testData testData = new alp_testData();
        HashMap responseBody = convertJsonToJava(response.asString(), HashMap.class);
        MatcherAssert.assertThat(responseBody.get("error"), is(equalTo(testData.errorMsgforBooks)));
    }
}
