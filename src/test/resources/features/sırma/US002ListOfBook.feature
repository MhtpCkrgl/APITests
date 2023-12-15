@WithoutToken  @listOf
Feature:GET Status
  Scenario Outline: List Of Book
    Given Kitaplari goruntuleyebilmek icin Get request hazirligi yapar
    When Kitaplari goruntuleyebilmek icin Get request gönderir
    And Listede  kitap oldugunu dogrular
    Then Kitaplarin isimlerinin "<kitaplar>" oldugunu dogrular
    And Stokta olmayan kitabın "Just as I Am" oldugunu dogrular
    Examples:
              | kitaplar            |
              |The Russian          |
              |The Vanishing Half   |
              |The Midnight Library |
              |Untamed              |
              |Viscount Who Loved Me|
              |Just as I Am         |
              |Viscount Who Loved Me|












