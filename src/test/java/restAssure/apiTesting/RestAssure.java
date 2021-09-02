package restAssure.apiTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssure {

    @Test
        public static void googleMapsAPI() {
            //To validate if AddAPI is working as expected
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            String response = given().log().all().contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(Payload.addPlace())
                    .when().post("maps/api/place/add/json?key=qaclick123")
                    .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract()
                    .response().asString();
            System.out.println(response);
            JsonPath js = new JsonPath(response);
            String place_id = js.getString("place_id");
            System.out.println("Place ID:" + place_id);

            //Update the place
            String newAddress = "70 winter walk, USA";
            given().log().all().contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body("{\n" +
                            "\"place_id\":\""+place_id+"\",\n" +
                            "\"address\":\""+newAddress+"\",\n" +
                            "\"key\":\"qaclick123\"\n" +
                            "}")
                    .when().put("maps/api/place/update/json")
                    .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
            System.out.println(newAddress);

            //get details of place
            String getPlaceResponse = given().log().all().contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .queryParam("key","qaclick123")
                    .queryParam("place_id",place_id)
                    .when().get("maps/api/place/get/json")
                    .then().assertThat().log().all().statusCode(200).extract().asString();
            System.out.println(getPlaceResponse);
            JsonPath js1 = new JsonPath(getPlaceResponse);
            String actualAddress = js1.getString("address");
            System.out.println("Actual Address:" +actualAddress);
            Assert.assertEquals(actualAddress,newAddress);
        }
}

