package testData;

import org.json.JSONArray;

public class alp_testData {

    public String registeredClientCredentials_ErrorMsg = "API client already registered. Try a different email.";

    public String status = "OK";


    public JSONArray expectedListOfBooks() {

        return new JSONArray(
                "[{\"id\":1,\"name\":\"The Russian\",\"type\":\"fiction\",\"available\":true}," +
                        "{\"id\":2,\"name\":\"Just as I Am\",\"type\":\"non-fiction\",\"available\":false}," +
                        "{\"id\":3,\"name\":\"The Vanishing Half\",\"type\":\"fiction\",\"available\":true}," +
                        "{\"id\":4,\"name\":\"The Midnight Library\",\"type\":\"fiction\",\"available\":true}," +
                        "{\"id\":5,\"name\":\"Untamed\",\"type\":\"non-fiction\",\"available\":true}," +
                        "{\"id\":6,\"name\":\"Viscount Who Loved Me\",\"type\":\"fiction\",\"available\":true}]"
        );


    }


    public JSONArray expectedListOfBooksAfterUsingParams() {

        return new JSONArray(
                "[{\"id\":2,\"name\":\"Just as I Am\",\"type\":\"non-fiction\",\"available\":false}," +
                        "{\"id\":5,\"name\":\"Untamed\",\"type\":\"non-fiction\",\"available\":true}]"
        );
    }


    public String errorMsgforBooks = "Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction.";








}
