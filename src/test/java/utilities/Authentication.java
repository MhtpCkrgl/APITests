package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
    public static void main(String[] args) {
        System.out.println(generateToken("username", "password"));
    }
    public static String generateToken(String clientName, String clientEmail) {
        Map<String, Object> token=new HashMap<>();
        token.put( "clientEmail", clientEmail);
        token.put("clientName", clientName);
        Response response=given().contentType(ContentType.JSON).body(token).
                post("https://.............................");
        response.prettyPrint();
        return response.jsonPath().getString("token");
    }
}