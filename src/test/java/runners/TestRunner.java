package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {
                "pretty",
                "summary",
                "timeline:target/timeline-report",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags = ""
)
public class TestRunner {

}
