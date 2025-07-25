package ExternalReports.OAuth;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) {
		
		String[] courseTtiles = { "Selenium Webdriver Java", "Cypress", "Protractorr" };


		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// Obtain access token using client credentials
		String accessTokenResponse = given()
			.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type", "client_credentials")
			.formParam("scope", "trust")
			.when().post("/oauthapi/oauth2/resourceOwner/token")
			.then().assertThat().statusCode(200).log().all().extract().asString();
			
		System.out.println("Access Token: " + accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		System.out.println("Extracted Token: " + accessToken);
		
		// Use the access token to access protected resource
		
		pojoClass pc = given()
			.queryParams("access_token", accessToken)
			.when().get("/oauthapi/getCourseDetails").as(pojoClass.class);
			
		System.out.println("LinkedIn: URL" + pc.getLinkedIn());
		System.out.println(pc.getInstructor());
		System.out.println(pc.getUrl());
		System.out.println(pc.getServices());
		System.out.println(pc.getExpertise());

		List<webAutomation> webCourses= pc.getCourses().getWebAutomation();
		
		List<API> apiCourses= pc.getCourses().getAPI();
		
		for(int i=0;i<apiCourses.size();i++){
			
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
				System.out.println("API Course Title: " + apiCourses.get(i).getCourseTitle());
				System.out.println("API Course Price: " + apiCourses.get(i).getPrice());
			}
		}


		ArrayList<String> actuArrayList = new ArrayList<String>();
		for(int i=0;i<webCourses.size();i++){
			actuArrayList.add(webCourses.get(i).getCourseTitle());
		}

		List<String> expectedList = Arrays.asList(courseTtiles);

		Assert.assertEquals(expectedList, actuArrayList);



			
		
		
		
	}

}
