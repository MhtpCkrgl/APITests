Feature:Submit An Order

  Scenario :Submit An Order
    Given Siparis olusturmak icin request hazirligi yapar
    When Siparis olusturmak icin  request gonderir
    Then status kodun 201 oldugunu dogrular
    And  Siparis olusturuldugu dogrulanir

