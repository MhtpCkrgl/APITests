Feature: US001-Status
  @WithoutToken
  Scenario: TC01-Api durum bilgisi dogrulanabilmeli
    Given Bengu-Api durumunu sorgulamak icin bir Get request gonder
    Then Bengu-Status Code 200 oldugunu dogrula
    And Bengu-Responstan "status" key dogrulamasi yap
