Feature: US009-Authentication

  @RunWithToken
  Scenario: TC01-Body eksiksiz girildiginde kayit islemi gerceklesmeli
    When Bengu-Register icin url duzenle ve POST request gonder
    Then Bengu-Status Code 201 oldugunu dogrula (auth)
    And Bengu-StatusCodeName created ve responsun accesToken icerdigini dogrula