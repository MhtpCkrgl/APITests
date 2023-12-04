package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utilities.Authentication.generateToken;

public class bookId {
    public static RequestSpecification spec;
    @Before
    public static void setUp(String clientName, String clientEmail) {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://simple-books-api.glitch.me")//base url
                .addHeader("Bearer", generateToken(clientName, clientEmail))//token
                .build();
    }
}
