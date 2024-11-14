package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;
import io.cucumber.java.Scenario;

public class ConfigExtentReport {

    private static ExtentReports extent;

    private static ExtentReports getExtent() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/Relatorio-de-testes.html");
            spark.config().setDocumentTitle("Relatório de Automação de Testes");
            spark.config().setReportName("Relatório de Testes Automatizados");
            spark.config().setEncoding("utf-8");
            spark.config().setTheme(Theme.DARK);
            spark.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
            spark.config().setTimelineEnabled(true);
            spark.config().setJs("$(document).ready(function(){viewOrder('dashboard','test','category','exception','author','device');});");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Usuário", ConfigReader.getProperty("author"));
            extent.setSystemInfo("Ambiente", ConfigReader.getProperty("environment"));
            extent.setSystemInfo("Navegador", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("Ambiente", System.getProperty("os.name"));
        }
        return extent;
    }

public static void generateReport(Scenario scenario) {
    ExtentReports extent = getExtent();

    String featureName = scenario.getUri().toString().substring(scenario.getUri().toString().lastIndexOf('/') + 1);
    String scenarioName = scenario.getName();

    ExtentTest test = extent.createTest(featureName + " - " + scenarioName)
            .assignAuthor("QA Team")
            .assignCategory("Testes de Regressão");
    if (scenario.isFailed()) {
        test.fail("O cenário falhou: " + scenarioName);
    } else {
        test.pass("O cenário passou com sucesso: " + scenarioName);
    }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

}


