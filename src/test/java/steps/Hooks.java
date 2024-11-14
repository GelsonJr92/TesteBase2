package steps;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ConfigExtentReport;
import utils.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Iniciando o WebDriver...");
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Encerrando o WebDriver...");
        ConfigExtentReport.generateReport(scenario);
        DriverManager.closeDriver();
    }

    @AfterAll
    public static void callGenerateExtentReport() {
        System.out.println("Finalizando e salvando o relatório de Testes...");
        ConfigExtentReport.flushReport();
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
