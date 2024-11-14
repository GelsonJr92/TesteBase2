package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitUtils {
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeoutInSeconds) {
        try {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(String.valueOf(timeoutInSeconds))));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("O tempo limite fornecido é inválido. Por favor, forneça um valor maior que 0.", e);
        }
    }


    public WebElement waitForVisibility(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("O elemento não ficou visível dentro do tempo limite: " + getElementInfo(element), e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("O elemento não foi encontrado na página: " + getElementInfo(element), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao esperar pela visibilidade do elemento: " + getElementInfo(element), e);
        }
    }


    public WebElement waitForClickability(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("O elemento não ficou clicável dentro do tempo limite: " + getElementInfo(element), e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("O elemento não foi encontrado na página: " + getElementInfo(element), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao esperar pela clickabilidade do elemento: " + getElementInfo(element), e);
        }
    }


    public WebElement waitForPresence(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("O elemento localizado por " + locator + " não foi encontrado dentro do tempo limite.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao esperar pela presença do elemento localizado por: " + locator, e);
        }
    }


    public void waitForPageLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            throw new TimeoutException("A página não carregou completamente dentro do tempo limite.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao esperar pelo carregamento completo da página.", e);
        }
    }


    private String getElementInfo(WebElement element) {
        try {
            return "Tag: " + element.getTagName() + ", Texto: " + element.getText() + ", Atributos: " + element.getAttribute("outerHTML");
        } catch (StaleElementReferenceException e) {
            return "O elemento não está mais presente no DOM.";
        } catch (Exception e) {
            return "Não foi possível obter informações do elemento.";
        }
    }
}
