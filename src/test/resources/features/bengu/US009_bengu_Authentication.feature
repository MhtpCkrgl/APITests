Feature: US009-Authentication

  @RunWithToken
  Scenario: TC01-Body eksiksiz girildiginde kayit islemi gerceklesmeli
    When Bengu-Register icin url duzenle ve POST request gonder
    Then Bengu-Status Code 201 oldugunu dogrula (auth)
    And Bengu-StatusCodeName created ve responsun accesToken icerdigini dogrula

  @RunWithToken
  Scenario: TC02/Negatif-Ayni mail adresi ile kayit islemi gerceklesmemeli
    When Bengu-Ayni mail adresi ile kayit olmak icin url duzenle ve POST request gonder
    Then Bengu-Status Code 400 oldugunu dogrula (auth)
    And Bengu-Responsdan kayit islemi gerceklesmedigini dogrula

  @RunWithToken
  Scenario: TC03/Negatif-Name bos birakildiginda kayit islemi gerceklesmemeli
    When Bengu-Name bos birakarak POST request gonder
    Then Bengu-Status Code 400 oldugunu dogrula (auth)
    And Bengu-Responsdan kayit islemi gerceklesmedigini dogrula

  @RunWithToken
  Scenario: TC04/Negatif-Email bos birakildiginda kayit islemi gerceklesmemeli
    When Bengu-Email bos birakarak POST request gonder
    Then Bengu-Status Code 400 oldugunu dogrula (auth)
    And Bengu-Responsdan kayit islemi gerceklesmedigini dogrula