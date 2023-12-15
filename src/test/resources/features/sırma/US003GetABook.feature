Feature:GET A Book
 @WithoutToken @dataa
  Scenario Outline:GET A Book
    Given Kullanici listeden kitap seçmek için request hazirligi yapar "<id>"
    When Kullanici listeden kitap seçmek için request gonderir
   Then status kodun 200 oldugunu dogrular
   And  Kitabın bilgilerinin dogru oldugunu dogrular "<id>", "<name>", "<author>","<type>","<price>","<current-stock>","<available>",
   Examples:
     |id   |name                  |author                             | type         |price   | current-stock| available  |
     |1    |The Russian           |James Patterson and James O. Born  |fiction       |12.98   | 12           |  true      |
     |2    |Just as I Am          |Cicely Tyson                       |non-fiction   |20.33   | 0            |  false     |
     |3    |The Vanishing Half    |Brit Bennett                       |fiction       |16.2    | 987          |  true      |
     |4    |The Midnight Library  |Matt Haig                          |fiction       |15.6    | 87           |  true      |
     |5    |Untamed               |Glennon Doyle                      |non-fiction   |15.5    | 20           |  true      |
     |6    |Viscount Who Loved Me |Julia Quinn                        |fiction       |15.6    | 1021         |  true      |


      # | id | name                | type        | available |
      # | 1 | The Russian          | fiction     | true   |
      # | 2 | Just as I Am         | non-fiction | false  |
      # | 3 | The Vanishing Half   | fiction     | true   |
      # | 4 | The Midnight Library | non-fiction | true   |
      # | 5 | Untamed              | non-fiction | true   |
      # | 6 | Viscount Who Loved Me| fiction     | true   |

