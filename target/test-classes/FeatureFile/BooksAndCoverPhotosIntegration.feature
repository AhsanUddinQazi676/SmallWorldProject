Feature: Integration Test for Books and Cover Photos

  Scenario: Validate association between book and cover photo
    Given I create books with the following details:
      | id | title  | description                                    | pageCount | excerpt                                         | publishDate                       |
      |  1 | Book 1 | Lorem lorem lorem. Lorem lorem lorem. Lorem... |       100 | Lorem lorem lorem. Lorem lorem lorem. Lorem ... | 2023-07-12T08:33:19.2288415+00:00 |
    When I retrieve the books details
    Then the cover photo associated with the book should have the following details:
      | url                                                                     |
      | https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 1&w=250&h=350 |
