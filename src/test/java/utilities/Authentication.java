package utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
    public static Faker faker;
    public static  String fakerName;
    public static  String fakerMail;

    public static String generateToken() {
        faker = new Faker();
        fakerName = faker.name().firstName();
        fakerMail = faker.internet().emailAddress();


        Map<String, Object> token = new HashMap<>();
        token.put("clientEmail", fakerMail);
        token.put("clientName", fakerName);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(token).
                post(ConfigReader.getProperty("urlToken"));
        response.prettyPrint();
        return response.jsonPath().getString("accessToken");

    }

    public static void main(String[] args) {
        System.out.println(generateToken());

    }
}