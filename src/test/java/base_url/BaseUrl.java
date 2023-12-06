package base_url;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static utilities.Authentication.generateToken;

public class BaseUrl {

    public static RequestSpecification spec;


    public static void setUp() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://simple-books-api.glitch.me")//base url
             //   .addHeader("Authorization","Bearer "+generateToken())//token
                .build();
    }

    public static void setUpWithAuth() {

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://simple-books-api.glitch.me")//base url
                .addHeader("Authorization","Bearer "+generateToken())//token
                .build();
    }



    @Test
    public void name() {
     //   System.out.println("generateToken() = " + generateToken());
        //"accessToken": "bce1119945de32eda8fa814b6a4ae757accda0191b0e8031ba45642543fb61c4"
    }


}
