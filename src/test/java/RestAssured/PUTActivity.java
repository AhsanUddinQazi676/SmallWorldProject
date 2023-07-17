package RestAssured;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import io.cucumber.core.cli.Main
import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PUTActivity {
    private Response response;
 // Rename method in PUTActivity class
    @Given("I ensure to Perform POST operation for {string} with body as in PUTActivity")
    public void i_ensure_to_perform_post_operation_for_with_body_as_in_put_activity(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Set body
        Map<String, String> body = data.get(0);

        // Perform post operation
        response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post(url);
    }

 // Rename method in PUTActivity class
    @And("^I Perform PUT operation for \"([^\"]*)\" in PUTActivity$")
    public void i_perform_put_operation_for_in_put_activity(String url, DataTable table) throws Throwable {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Set body
        Map<String, String> body = data.get(0);

        // Set path parameters
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", body.get("id"));

        // Perform PUT operation
        response = RestAssured.given()
                .contentType("application/json")
                .pathParams(pathParams)
                .body(body)
                .put(url);
    }

    @Given("I perform GET operation with path parameter for {string} in PUTActivity")
    public void i_perform_get_operation_with_path_parameter_for_in_put_activity(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        // Set path parameters
        Map<String, String> pathParams = data.get(0);

        // Perform get operation
        response = RestAssured.given()
                .pathParams(pathParams)
                .get(url);
    }


    @Then("I {string} see the body with title as {string} in PUTActivity")
    public void i_see_the_body_with_title_as_in_put_activity(String shouldSee, String expectedTitle) {
        if (shouldSee.equals("should not")) {
            response.then().assertThat().body("title", Matchers.not(Matchers.equalTo(expectedTitle)));
        } else {
            response.then().assertThat().body("title", Matchers.equalTo(expectedTitle));
        }
    }
}
