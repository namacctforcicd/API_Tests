package Base;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

 import static io.restassured.RestAssured.*;

public class DynamicJSON {
// https://reqres.in/
    @Test(dataProvider = "bookData")
    public void addBook(String aisle,String isbn){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
      String bookAdd=  given().log().all()
                .header("Content-Type","application/json").body(Payload.addBook(aisle,isbn))
                .when().post("/Library/Addbook.php").then().log().all()
              .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsp = ReUsableMethods.rawToJson(bookAdd);
        String ID = jsp.get("ID");
        System.out.println(ID);
        System.out.println(bookAdd);
    }

    @DataProvider(name="bookData")
    public Object[][] getData(){
       return new Object[][]  {{"901","4B6"}, {"109","5H8"},{"490","2K6"}};
    }
}
