package stepDefinition.ApiStepDefinition;

public class örnek {
    // Faker faker =new Faker();
    //    public static AdminPostPojo expectedData;
    //    Response response;
    //    public static int AdminId;
    //
    //    public static AdminPostResponse actualData;
    //
    //    @Given("Admin hesabı eklemek icin post request hazirligi yapilir")
    //    public void adminHesabıEklemekIcinPostRequestHazirligiYapilir() {
    //        setUp("AdminTeam15","AdminTeam15");
    //        spec.pathParams("first","admin","second","save");
    //    }
    //
    //    @And("Admin bilgileri hazirlanir")
    //    public void adminBilgileriHazirlanir() {
    //        String birthDay = ReusableMethods.fakeBirthDate("yyyy-MM-dd");
    //        String birthPlace = ReusableMethods.generatebirthPlace(2,17);
    //        String name= faker.name().firstName();
    //        String phoneNumber = ReusableMethods.randomPhone();
    //        String ssn = faker.idNumber().ssnValid();
    //        String surname= faker.name().lastName();
    //        String userName = ReusableMethods.generateUserName(4,17);
    //        String password = ReusableMethods.randomPassword(2,3,3);
    //        expectedData = new AdminPostPojo(birthDay,birthPlace,true,"MALE",name, password, phoneNumber,ssn,surname,userName);
    //
    //    }
    //
    //    @When("Admin eklemek icin post request yapilir")
    //    public void adminEklemekIcinPostRequestYapilir() {
    //        response = given(spec).body(expectedData).when().post("{first}/{second}");
    //        AdminId = response.jsonPath().getInt("object.userId");
    //        actualData = response.as(AdminPostResponse.class);
    //    }
    //
    //    @Then("Admin bilgileri kontrol edilir")
    //    public void adminBilgileriKontrolEdilir() {
    //        assertEquals(200,response.statusCode());
    //        assertEquals(expectedData.getBirthDay(),actualData.getObject().getBirthDay());
    //        assertEquals(expectedData.getBirthPlace(),actualData.getObject().getBirthPlace());
    //        assertEquals(expectedData.getGender(),actualData.getObject().getGender());
    //        assertEquals(expectedData.getName(),actualData.getObject().getName());
    //        assertEquals(expectedData.getSsn(),actualData.getObject().getSsn());
    //        assertEquals(expectedData.getPhoneNumber(),actualData.getObject().getPhoneNumber());
    //        assertEquals(expectedData.getSurname(),actualData.getObject().getSurname());
    //        assertEquals(expectedData.getUsername(),actualData.getObject().getUsername());
    //    }
    //
    //    @Given("Kayitli Admin hesabi icin delete request hazirligi yapilir")
    //    public void kayitliAdminHesabiIcinDeleteRequestHazirligiYapilir() {
    //        setUp("AdminTeam15","AdminTeam15");
    //        spec.pathParams("first","admin","second","delete","third",AdminId);
    //    }
    //
    //    @When("Kayitli Admin hesabi silinir")
    //    public void kayitliAdminHesabiSilinir() {
    //        response = given(spec).when().delete("{first}/{second}/{third}");
    //    }
    //
    //    @Then("Admin hesabinın silindigi dogrulanir")
    //    public void adminHesabinınSilindigiDogrulanir() {
    //        String expectedData = "Admin deleted Successful";
    //        assertEquals(200,response.statusCode());
    //        assertEquals(expectedData,response.asString());
    //    }
}
