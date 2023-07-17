Feature: Integration Test for Books and Authors

  Scenario: Validate association between book and author
    Given I create an author with the following details:
      | id | idBook | firstName    | lastName    |
      |  1 |      1 | First Name 1 | Last Name 1 |
    And I create a book with the following details:
      | id | title  | description                                    | pageCount | excerpt                                         | publishDate                       |
      |  1 | Book 1 | Lorem lorem lorem. Lorem lorem lorem. Lorem... |       100 | Lorem lorem lorem. Lorem lorem lorem. Lorem ... | 2023-07-12T08:33:19.2288415+00:00 |
    When I retrieve the book details
    Then the author associated with the book should have the following details:
      | firstName    | lastName    |
      | First Name 1 | Last Name 1 |
