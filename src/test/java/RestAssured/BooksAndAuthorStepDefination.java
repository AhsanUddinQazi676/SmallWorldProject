package RestAssured;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksAndAuthorStepDefination {
    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net/api/v1";
    private static final String AUTHORS_ENDPOINT = "/Authors";
    private static final String BOOKS_ENDPOINT = "/Books";

    private Response authorResponse;
    private Response bookResponse;
    private Response retrievedBookResponse;
    private Response retrievedAuthorResponse;

    @Given("I create an author with the following details:")
    public void createAuthor(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> authorData = data.get(0);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", Integer.parseInt(authorData.get("id")));
        requestBody.put("idBook", Integer.parseInt(authorData.get("idBook")));
        requestBody.put("firstName", authorData.get("firstName"));
        requestBody.put("lastName", authorData.get("lastName"));

        authorResponse = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + AUTHORS_ENDPOINT);

        System.out.println("Response Body: " + authorResponse.getBody().asString());
    }

    @Given("I create a book with the following details:")
    public void createBook(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> bookData = data.get(0);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", Integer.parseInt(bookData.get("id")));
        requestBody.put("title", bookData.get("title"));
        requestBody.put("description", bookData.get("description"));
        requestBody.put("pageCount", Integer.parseInt(bookData.get("pageCount")));
        requestBody.put("excerpt", bookData.get("excerpt"));
        requestBody.put("publishDate", bookData.get("publishDate"));

        bookResponse = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + BOOKS_ENDPOINT);
        System.out.println("Response Body: " + bookResponse.getBody().asString());
    }

    @When("I retrieve the book details")
    public void retrieveBookDetails() {
        String bookUrl = BASE_URL + BOOKS_ENDPOINT + "/1"; // Replace "1" with the actual book ID
        retrievedBookResponse = RestAssured.get(bookUrl);

        Assert.assertEquals(200, retrievedBookResponse.getStatusCode());
        Assert.assertEquals("Book 1", retrievedBookResponse.jsonPath().getString("title"));
        Assert.assertEquals("Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n", retrievedBookResponse.jsonPath().getString("description"));
        Assert.assertEquals(100, retrievedBookResponse.jsonPath().getInt("pageCount"));

        System.out.println("Response Body: " + retrievedBookResponse.getBody().asString());
    }

    @Then("the author associated with the book should have the following details:")
    public void verifyAuthorDetails(DataTable dataTable) {
        List<Map<String, String>> authorData = dataTable.asMaps(String.class, String.class);

        String expectedFirstName = authorData.get(0).get("firstName");
        String expectedLastName = authorData.get(0).get("lastName");

        String authorUrl = BASE_URL + AUTHORS_ENDPOINT + "/1"; // Replace "{authorId}" with the actual author ID
        retrievedAuthorResponse = RestAssured.get(authorUrl);

        Assert.assertEquals(200, retrievedAuthorResponse.getStatusCode());
        Assert.assertEquals(expectedFirstName, retrievedAuthorResponse.jsonPath().getString("firstName"));
        Assert.assertEquals(expectedLastName, retrievedAuthorResponse.jsonPath().getString("lastName"));

        System.out.println("Response Body: " + retrievedAuthorResponse.getBody().asString());
    }
}
