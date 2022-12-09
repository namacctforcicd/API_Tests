package Base;

import POJO.GetCourses;
import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;


public class OAuthTest {


    public static void main(String[] args){
      // RestAssured.baseURI = "https://rahulshettyacademy.com/";

//        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
//        //  driver = new ChromeDriver();
//     WebDriver driver = new FirefoxDriver();
//     driver.get("https://www.googleapis.com/oauth2/v4/token?code=4%2F0AY0e-g6UUD1-sMKIpsxzb520ZVflKe7i8xD_oQk6X3t_fjDqhaROnrJncgGX7dEF0jllVg&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code#");
//     driver.manage().window().maximize();
//        WebElement inputBox = driver.findElement(By.xpath("//input[@id='identifierId']"));
//        inputBox.sendKeys("nnamdiwill");
//       // inputBox.sendKeys(Keys.ENTER);
//        WebElement nextButton = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d']"));
//        nextButton.click();
//        WebElement passwordBox = driver.findElement(By.name("password"));
//        passwordBox.sendKeys(Keys.ENTER);

       // String url = driver.getCurrentUrl();
        String url = "https://rahulshettyacademy.com/getCourse.php?access_token=ya29.a0AfH6SMD-EytFH41srRFqd55ewFQGIJDcuEq-uhPn4UYeXmyRkY5NY0VBPYZgAk2zou8KRYZpspkrC3QtrhKS5McUsWoxCFpWoYEVFq3rN00eJCicj-_j9xv8Q-9gTumTGy5rJxgfkMrTVZSbtWnB2LSR0C1L";

      String partialCode =  url.split("code=")[1];
     String code =  partialCode.split("&scope")[0];
     System.out.println(code);

      String accessToken =   given().urlEncodingEnabled(false) // to eliminate special characters in access token
                .queryParam("code",code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsp = new JsonPath(accessToken);
        String getAccess = jsp.get("access_token");

      GetCourses response =   given().log().all().queryParam("access_token",getAccess ).expect()
              .defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .as(GetCourses.class);
     // System.out.println(response);
        System.out.println(response.getLinkedIn());
        System.out.println(response.getInstructor());
    }
}
