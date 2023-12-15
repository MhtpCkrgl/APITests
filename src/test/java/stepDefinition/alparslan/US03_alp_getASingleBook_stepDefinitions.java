package stepDefinition.alparslan;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import pojos.alp_singleBookInfoPOJO;
import stepDefinition.fatma.BookPOJO;
import testData.GetBookTestData;
import utilities.ObjectMapperUtils;

import static base_url.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static stepDefinition.alparslan.US02_alp_getListOfBooks_stepDefinitions.alp_availableBookId;
import static stepDefinition.alparslan.US02_alp_getListOfBooks_stepDefinitions.alp_unAvailableBookId;


public class US03_alp_getASingleBook_stepDefinitions {


    Response response;

    @When("alp User sends GET Request to get a single book")
    public void alpUserSendsGETRequestToGetASingleBook() {
        alp_availableBookId = 5;  // ----> BU SATIR SİLİNECEK
//        System.out.println("alp_availableBookId = " + alp_availableBookId);
//        System.out.println("alp_unAvailableBookId = " + alp_unAvailableBookId);

        spec.pathParams("param1", "books", "param2", alp_availableBookId);

        response = given()
                .spec(spec)
                .when()
                .get("{param1}/{param2}");
        response.prettyPrint();
    }

    @Then("alp Verifies status code iss {int}")
    public void alpVerifiesStatusCodeIss(int statusCode) {
        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(statusCode);
    }

    @And("alp User gets a single book and validates")
    public void alpUserGetsASingleBookAndValidates() {

        alp_singleBookInfoPOJO expectedData = new alp_singleBookInfoPOJO(5,
                "Untamed",
                "Glennon Doyle",
                "non-fiction",
                15.5,
                20,
                true);

        String responseStr = response.asString().replaceAll("current-stock", "currentStock");
        alp_singleBookInfoPOJO responseBody = ObjectMapperUtils.convertJsonToJava(responseStr, alp_singleBookInfoPOJO.class);


        MatcherAssert.assertThat(expectedData.getId(), is(equalTo(responseBody.getId())));
        MatcherAssert.assertThat(expectedData.getName(), is(equalTo(responseBody.getName())));
        MatcherAssert.assertThat(expectedData.getAuthor(), is(equalTo(responseBody.getAuthor())));
        MatcherAssert.assertThat(expectedData.getType(), is(equalTo(responseBody.getType())));
        MatcherAssert.assertThat(expectedData.getPrice(), is(equalTo(responseBody.getPrice())));
        MatcherAssert.assertThat(expectedData.getCurrentStock(), is(equalTo(responseBody.getCurrentStock())));
        MatcherAssert.assertThat(expectedData.isAvailable(), is(equalTo(responseBody.isAvailable())));





    }

    @And("alp Validates response body")
    public void alpValidatesResponseBody() {
    }

    @When("alp User sends GET Request to get a single book with invalid id")
    public void alpUserSendsGETRequestToGetASingleBookWithInvalidId() {
    }


}
