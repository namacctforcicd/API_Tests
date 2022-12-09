package StepDefinitionGlueCode;

import Base.StarwarsHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StarWarsStepDefs {

    StarwarsHomePage swhp = new StarwarsHomePage();

    @Given("^I go to Star wars homepage$")
    public void openHomePage(){

        swhp.openingStarWarPage();
    }

    @When("^I send out the test Star wars API$")
    public void testAPI(){

        swhp.readyForResponse();
    }

    @Then("^I get the proper response$")
    public void verifyResponse(){

        swhp.responseForPeople();
    }

    @Given("^I go back to Star wars homepage$")
    public void backtoHomePage(){

        swhp.openingStarWarPage();
    }

    @When("^I get the Planet endpoint$")
    public void getPlanetEndpoint(){

        swhp.readyForResponse();
    }

    @Then("^I get the planet API response$")
    public void planetResponseCode(){

        swhp.responseForPlanet();
    }
}
