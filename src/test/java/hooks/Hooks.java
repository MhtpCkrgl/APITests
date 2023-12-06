package hooks;

import io.cucumber.java.Before;

import static base_url.BaseUrl.setUp;
import static base_url.BaseUrl.setUpWithAuth;


public class Hooks {


    @Before("@WithoutToken")
    public void beforeApi() {
        setUp();

    }



    @Before("@RunWithToken")
    public void beforeAPiWithToken() {
        setUpWithAuth();

    }





}
