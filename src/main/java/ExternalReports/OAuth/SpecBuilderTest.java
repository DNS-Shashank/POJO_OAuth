package ExternalReports.OAuth;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import POJO.addPlace;
import POJO.addPlace_location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

    public static void main(String[] args) {

       RequestSpecification request =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
        .addQueryParam("key", "qaclick123")
        .setContentType(ContentType.JSON).build();


        ResponseSpecification response = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON).build();

        addPlace p = new addPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 9876546512");
        p.setName("Frontline house");
        p.setWebsite("29, side layout, cohen 09");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        addPlace_location loc = new addPlace_location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        p.setLocation(loc);

        RequestSpecification addPlace = given().log().all().spec(request).body(p);

        Response addPlaceResponse= addPlace.when().post("/maps/api/place/add/json")
		.then().spec(response).extract().response();

        String responseString = addPlaceResponse.asString();

        System.out.println(responseString);


    }
}
