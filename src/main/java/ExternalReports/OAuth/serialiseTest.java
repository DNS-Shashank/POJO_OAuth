package ExternalReports.OAuth;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import POJO.addPlace;
import POJO.addPlace_location;
import io.restassured.RestAssured;

public class serialiseTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

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

        String addPlaceResponse = 
        given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body(p).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();

        System.out.println(addPlaceResponse);


    }
}
