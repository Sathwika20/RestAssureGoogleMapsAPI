package restAssure.apiTesting;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StaticJson {
    @Test
    public static void addPlace() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\gopir\\OneDrive\\Desktop\\addplace.json"))))
                .when().post("maps/api/place/add/json?key=qaclick123")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract()
                .response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        System.out.println("Place ID:" + place_id);
    }
}
