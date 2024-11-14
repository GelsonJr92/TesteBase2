package pages;

import config.Config;
import config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, Config.getExplicitTimeout());
        PageFactory.initElements(driver, this);
    }

    public void navigateToBaseUrl(String url) {
        driver.get(url);
    }

    public void validarTexto(WebElement elemento, String textoEsperado) {
        waitUtils.waitForPageLoad();
        WebElement visibleElement = waitUtils.waitForVisibility(elemento);
        String textoAtual = visibleElement.getText();
        if (textoAtual.equals(textoEsperado)) {
            System.out.println("Validação bem-sucedida. O texto corresponde exatamente: " + textoAtual);
        } else if (textoAtual.contains(textoEsperado)) {
            System.out.println("Validação bem-sucedida. O texto contém a parte esperada: " + textoEsperado);
        } else {
            throw new AssertionError("Texto não corresponde. Texto atual: '" + textoAtual + "', Texto esperado ou parte do texto: '" + textoEsperado + "'");
        }
    }

    public void clicarElemento(WebElement element) {
        int explicitTimeout = Integer.parseInt(ConfigReader.getProperty("explicit.timeout"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void writeText(WebElement element, String text) {
        try {
            WebElement visibleElement = waitUtils.waitForVisibility(element);
            waitUtils.waitForClickability(visibleElement);
            visibleElement.clear();
            visibleElement.sendKeys(text);
            System.out.println("Texto inserido com sucesso: " + text);
        } catch (TimeoutException e) {
            throw new TimeoutException("O elemento não ficou pronto para interação dentro do tempo limite.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("O elemento ficou obsoleto durante a interação.", e);
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("O elemento não está interagível.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao tentar inserir texto no elemento.", e);
        }
    }

    public void pressEnter() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
            System.out.println("Tecla Enter simulada no contexto da página.");
        } catch (Exception e) {
            System.err.println("Erro ao simular a tecla Enter: " + e.getMessage());
        }
    }
    public static void uploadFile(WebElement fileInput, String filePath) {
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Arquivo não encontrado ou não é um arquivo válido: " + filePath);
        }
        try {
            fileInput.sendKeys(file.getAbsolutePath());
            System.out.println("Arquivo enviado com sucesso: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro ao enviar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void validarMensagemDeErro(WebElement campo, String mensagemEsperada) {
        try {
                if (!campo.isDisplayed()) {
                throw new IllegalArgumentException("O campo não está visível na tela.");
            }
            String mensagemAtual = campo.getAttribute("validationMessage");
            if (mensagemAtual.equals(mensagemEsperada)) {
                System.out.println("Validação bem-sucedida. Mensagem encontrada: " + mensagemAtual);
            } else {
                throw new AssertionError("Mensagem esperada: '" + mensagemEsperada + "', mas foi: '" + mensagemAtual + "'");
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar a mensagem de erro: " + e.getMessage());
        }
    }
}


