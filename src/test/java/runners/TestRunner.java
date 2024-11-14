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
                //"json:target/cucumber-reports/CucumberTestReport.json",
                //"html:target/cucumber-reports/cucumber-pretty.html",
                "timeline:target/timeline-report", // Relatório de linha do tempo
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags = ""
)
public class TestRunner {

}
