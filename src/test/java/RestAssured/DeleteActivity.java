package RestAssured;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteActivity {
    private Response response;

    @Given("I ensure to Perform POST operation for {string} with body as")
    public void i_ensure_to_perform_post_operation_for_with_body_as(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Set body
        Map<String, String> body = data.get(0);

        // Perform post operation
        response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(url);
    }

    @Given("I Perform DELETE operation for {string}")
    public void iPerformDeleteOperationFor(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Get the value of the id path parameter
        String id = data.get(0).get("id");

        // Update the URL by replacing the path parameter with the actual value
        String deleteUrl = url.replace("{id}", id);

        // Perform the DELETE operation
        response = RestAssured.delete(deleteUrl);
    }

    @When("I perform GET operation with path parameter for {string}")
    public void i_perform_get_operation_with_path_parameter_for(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Set path parameters
        Map<String, String> pathParams = data.get(0);

        // Perform get operation
        response = RestAssured.get(url, pathParams);
    }

    @Then("I {string} see the body with title as {string}")
    public void i_see_the_body_with_title_as(String shouldSee, String expectedTitle) {
        if (shouldSee.equals("should not")) {
            response.then().assertThat().body("title", Matchers.not(Matchers.equalTo(expectedTitle)));
        } else {
            response.then().assertThat().body("title", Matchers.equalTo(expectedTitle));
        }
    }
}
