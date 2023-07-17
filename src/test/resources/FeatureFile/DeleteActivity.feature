Feature: DeletePosts
  Test the delete operation

  @Test
  Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation for "https://fakerestapi.azurewebsites.net/api/v1/Activities" with body as
      | id | title  | dueDate                  | completed |
      |  1 | string | 2023-07-12T12:09:08.775Z | true      |
    And I Perform DELETE operation for "https://fakerestapi.azurewebsites.net/api/v1/Activities/{id}"
      | id |
      |  1 |
    And I perform GET operation with path parameter for "https://fakerestapi.azurewebsites.net/api/v1/Activities/{id}"
      | id |
      |  1 |
    Then I "should" see the body with title as "Activity 1"
