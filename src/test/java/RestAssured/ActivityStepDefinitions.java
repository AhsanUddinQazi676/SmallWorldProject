package RestAssured;
import org.testng.annotations.Test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ActivityStepDefinitions {

//	@Given("I perform Get operation for {string}")
//	public void i_perform_get_operation_for(String string) {
////		given().contentType(ContentType.JSON);
//	}
	
//	@When("use get the URl")
//	public void use_get_the_u_rl() {
//		when()
//		.get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
//		.then()
//		.statusCode(200)
//		.statusLine("HTTP/1.1 200 OK")
//		.assertThat().body("title", equalTo("Activity 1"))	
//		.header("Content-Type", containsString("application/json"));
//		
//	}
//	
	@And("I perform GET for the post number {string}")
	public void i_perform_get_for_the_post_number(String postNumber) {
		GETAndPOSTActivity.Simplegetpost(postNumber);
//		when()
//        .get(String.format("https://fakerestapi.azurewebsites.net/api/v1/Activities", postNumber))
//        .then()
//        .statusCode(200)
//        .statusLine("HTTP/1.1 200 OK")
//        .assertThat().body("title", hasItem("Activity 1"))
//        .header("Content-Type", containsString("application/json"));
	}
	
	@Then("I should see the author name as {string}")
	public void i_should_see_the_author_name_as(String string) {
//		System.out.println("Inside Step Defination");
}

	@Then("I should see verify GET Parameter")
	public void i_should_see_verify_get_parameter() {
		GETAndPOSTActivity.PerformPathParameter();
	}
	
	
	@Given("I perform Get operation for {string}")
	public void i_perform_get_operation_for_Activity(String string) {
		GETAndPOSTActivity.PerformPOSTWithBodyParameter();
	}
	

}
