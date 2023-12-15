package testData;


import base_url.BaseUrl;

import java.util.HashMap;
import java.util.Map;

public class GetBookTestData extends BaseUrl {
    public static Map<String,Object>  getListOfBooks(Integer id, String name, String type, Boolean available) {
        Map<String, Object> listofBooks = new HashMap<>();
        if (id != null) {
            listofBooks.put("id", id);
        }
        if (name != null) {
            listofBooks.put("name", name);
        }
        if (type != null) {
            listofBooks.put("type", type);
        }
        if (available != null) {
            listofBooks.put("available", available);
        }
            return listofBooks;
        }


        public static String convertJsonToString (String id, String name, String author, String type, Object price, String currentstock, String available){
            return "{\n" +
                    "\"id\": "+id+",\n" +
                    "\"name\": \""+name+"\",\n" +
                    "\"author\": \""+author+"\",\n" +
                    "\"type\": \""+type+"\",\n" +
                    "\"price\": \""+price+"\",\n" +
                    "\"currentstock\": \""+currentstock+"\",\n" +
                    "\"available\": \""+available+"\"\n" +
                    "}";
        }
    public static String convertJsonToString_2 (String id, String name, String type, String available){
        return "{\n" +
                "\"id\": "+id+",\n" +
                "\"name\": \""+name+"\",\n" +
                "\"type\": \""+type+"\",\n" +
                "\"available\": \""+available+"\"\n" +
                "}";
    }

    }

