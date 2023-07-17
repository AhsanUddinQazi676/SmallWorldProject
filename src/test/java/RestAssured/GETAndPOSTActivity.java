package RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

import io.restassured.http.ContentType;

public class GETAndPOSTActivity {

	
	public static void Simplegetpost(String postNumber)
	{
		given().contentType(ContentType.JSON).when()
        .get(String.format("https://fakerestapi.azurewebsites.net/api/v1/Activities", postNumber))
        .then()
        .statusCode(200)
        .statusLine("HTTP/1.1 200 OK")
        .assertThat().body("title", hasItem("Activity 1"))
        .header("Content-Type", containsString("application/json"));
	}
	
	
	
	
	public static void PerformPathParameter()
	{
		
		given().contentType(ContentType.JSON).with().pathParams("Activities", 2)
		.then()
        .statusCode(200)
        .statusLine("HTTP/1.1 200 OK")
        .body("title", hasItem("Activity 2"))
        .header("Content-Type", containsString("application/json"));
	}
	
	public static void PerformPOSTWithBodyParameter() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"id\": 32, \"title\": \"string\", \"dueDate\": \"2023-07-12T12:09:08.775Z\", \"completed\": true}")
        .when()
            .post("https://fakerestapi.azurewebsites.net/api/v1/Activities")
        .then()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .body("title", equalTo("string"))
            .header("Content-Type", containsString("application/json"));
    }
	
	
	
}
