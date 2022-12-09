package StepDefinitionGlueCode;

import Base.BDDStyleMethod;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class Test_StepsforUserList {

    @Given("^I want to execute getUserList Endpoint$")
    public void beginTest() throws Throwable {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api/users/2";
    }

    @When("^I submit the GET request$")
    public void submitGet() throws Throwable {
        BDDStyleMethod.getResponse();
    }

    @Then("^I should get 200 response code$")
    public void getResponseCode() {
        try {
            Response r = BDDStyleMethod.getResponse();
            int statusCode = r.getStatusCode();
            Assert.assertEquals(statusCode, 200);
        } catch (NullPointerException npe) {
            System.out.println(npe.getStackTrace());
        }
    }
}