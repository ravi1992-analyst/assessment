import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features = {"src/main/resources/FeatureFiles/test123.feature"},
        glue = {"src/main/java/com/stepDefinition", "src/main/java/com/myHooks"},
        plugin = {"pretty","html:src/cucumber.html"},
        publish = true
)
public class Runner extends AbstractTestNGCucumberTests {
}
