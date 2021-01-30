package runner;

import io.cucumber.testng.*;

@CucumberOptions(
    features = "src/test/resources/cucumber",
    glue = {"cucumber", "runner"},
    plugin = {"pretty"})
public class CucumberTest extends AbstractTestNGCucumberTests {
}
