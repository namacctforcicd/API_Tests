package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/feature/starwars.feature",
        glue = {"StepDefinitionGlueCode"},
        plugin = {"pretty"},
        publish = true,
        tags="@sw2"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
