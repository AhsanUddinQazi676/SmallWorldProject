Feature: PutPostsOperation Test the Put operation

  @Test
  Scenario: Verify PUT operation after POST
    Given I ensure to Perform POST operation for "https://fakerestapi.azurewebsites.net/api/v1/Activities" with body as in PUTActivity
      | id | title  | dueDate                  | completed |
      |  0 | string | 2023-07-12T12:09:08.775Z | true      |
    And I Perform PUT operation for "https://fakerestapi.azurewebsites.net/api/v1/Activities/{id}" in PUTActivity
      | id |
      |  1 |
    And I perform GET operation with path parameter for "https://fakerestapi.azurewebsites.net/api/v1/Activities/{id}" in PUTActivity
      | id |
      |  1 |
    Then I "should not" see the body with title as "Activity 1" in PUTActivity
