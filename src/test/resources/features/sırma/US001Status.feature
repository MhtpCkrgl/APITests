
Feature:GET Status
  @WithoutToken  @scenario01
  Scenario:GET Status
    Given Apinin status kodu icin Get request hazirligi yapilir
    When Apinin status kodu icin Get request gönderilir
    Then Response un status kodunun 200 olduğu doğrulanır

