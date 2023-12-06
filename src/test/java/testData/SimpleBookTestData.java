package testData;


import base_url.BaseUrl;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class SimpleBookTestData extends BaseUrl {
    //orderBook request gonderirken olusturacagimiz payload icin kullanacagimiz method
    public Map<String,Object> orderBookRequestMethod(Integer bookId, String customerName){
               Map<String,Object> orderBookData = new HashMap<>();
               if (bookId!=null){
                   orderBookData.put("bookId",bookId);
               }
               if (customerName!=null){
                  orderBookData.put("customerName",customerName);
               }
               return orderBookData;
            }
            //set the expected data kisminda kullnacagimiz ornek sekil asagida verilmistir
    /*
     //set the expected data
        SimpleBookTestData obj = new SimpleBookTestData();
        Map<String,Object> payLoad =obj.orderBookRequestMethod(5, James B);
     */

            //orderBook yaptigimizda gelen request icin kullanacagimiz method. Bu method json olarak gelen methodu javanin anlayacagi
    public static String convertJsonToString(Boolean created,String orderId){
        return "{\n" +
                "\"created\": "+created+",\n" +
                "\"userId\": \""+orderId+"\",\n" +
                "}";
        //response asssertion yaparken kullanacagimiz data
     // Response response=given(spec).when()
       // Map<String,Object> actualData =convertJsonToJava(response.asString(),HashMap.class);


    }
}
