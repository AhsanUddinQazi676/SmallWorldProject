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

public class BooksAndCoverPhotoStepDefination {
    private static final String BASE_URL = "https://fakerestapi.azurewebsites.net/api/v1";
    private static final String BOOKS_ENDPOINT = "/Books";
    private static final String COVER_PHOTOS_ENDPOINT = "/CoverPhotos/books/covers";

    private Response bookResponse;
    private Response retrievedBooksResponse;
    private Response retrievedCoverPhotoResponse;

    @Given("I create books with the following details:")
    public void createBooksCoverPhoto(DataTable dataTable) {
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
        
        System.out.println("Response Body (Create Book): " + bookResponse.getBody().asString());
    }

    @When("I retrieve the books details")
    public void retrieveBooksDetailsCoverPhoto() {
        String bookUrl = BASE_URL + BOOKS_ENDPOINT + "/1"; // Replace "1" with the actual book ID
        retrievedBooksResponse = RestAssured.get(bookUrl);

        Assert.assertEquals(200, retrievedBooksResponse.getStatusCode());
        Assert.assertEquals("Book 1", retrievedBooksResponse.jsonPath().getString("title"));
        Assert.assertEquals("Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n", retrievedBooksResponse.jsonPath().getString("description"));
        Assert.assertEquals(100, retrievedBooksResponse.jsonPath().getInt("pageCount"));
        
        System.out.println("Response Body (Retrieve Book): " + retrievedBooksResponse.getBody().asString());
    }

    @Then("the cover photo associated with the book should have the following details:")
    public void verifyCoverPhotoDetails(DataTable dataTable) {
        List<Map<String, String>> coverPhotoData = dataTable.asMaps(String.class, String.class);

        String expectedCoverPhotoUrl = coverPhotoData.get(0).get("url");

        String coverPhotoUrl = BASE_URL + COVER_PHOTOS_ENDPOINT + "/1"; // Replace "1" with the actual book ID
        retrievedCoverPhotoResponse = RestAssured.get(coverPhotoUrl);

        Assert.assertEquals(200, retrievedCoverPhotoResponse.getStatusCode());
        Assert.assertEquals(expectedCoverPhotoUrl, retrievedCoverPhotoResponse.jsonPath().getString("url").replaceAll("\\[|\\]", ""));


        System.out.println("Response Body (Retrieve Cover Photo): " + retrievedCoverPhotoResponse.getBody().asString());
    }
}
