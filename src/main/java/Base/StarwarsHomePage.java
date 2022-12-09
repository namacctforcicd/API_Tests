package Base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class StarwarsHomePage {

    public void openingStarWarPage(){
        RestAssured.baseURI = "https://swapi.dev/api";
       // RestAssured.basePath = "/people/1/";

    }

    public void responseForPeople(){
        String getPlaceResponse = given().log().all().when().get("/people")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
      //  System.out.println(getPlaceResponse);
    }

    public void readyForResponse(){

        System.out.println("***********The response is below**************");
    }

    public void responseForPlanet(){
        String getPlanetResponse = given().log().all().when().get("/planets/")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
    }
}
