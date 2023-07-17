Feature: Verify Different GET Activities using REST-assured

  @Test
  Scenario: verify one author of the post
    Given I perform Get operation for "/post"
    #When use get the URl
    And I perform GET for the post number "1"
    Then I should see the author name as "Ahsan Uddin"
	@Test
  Scenario: verify the path perameter Get Method
    Given I perform Get operation for "/post"
    #When use get the URl
    And I perform GET for the post number "1"
    Then I should see verify GET Parameter
	@Test
  Scenario: verify Post operation with Perameter 
    Given I perform Get operation for "/Activity"
