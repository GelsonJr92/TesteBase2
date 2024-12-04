package pages;

import config.Config;
import config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;


public class BaseTest {
    protected WebDriver driver;
    protected WaitUtils waitUtils;
    private static final int EXPLICIT_TIMEOUT = Integer.parseInt(ConfigReader.getProperty("explicit.timeout"));


    public BaseTest(WebDriver driver) {
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

    public void clicarElemento(Object element) {
        try {
            int explicitTimeout = Integer.parseInt(ConfigReader.getProperty("explicit.timeout"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));

            WebElement targetElement;
            if (element instanceof WebElement) {
                targetElement = (WebElement) element;
            } else if (element instanceof By) {
                targetElement = wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
            } else {
                throw new IllegalArgumentException("Tipo de elemento não suportado. Use WebElement ou By.");
            }
            targetElement = wait.until(ExpectedConditions.visibilityOf(targetElement));
            targetElement = wait.until(ExpectedConditions.elementToBeClickable(targetElement));
            if (isElementInViewport(targetElement)) {
                targetElement.click();
                System.out.println("Clique realizado com sucesso no elemento: " + targetElement);
            } else {
                throw new RuntimeException("O elemento não está visível na viewport.");
            }
        } catch (TimeoutException e) {
            throw new TimeoutException("O elemento não ficou disponível para clique dentro do tempo limite.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("O elemento ficou obsoleto durante a tentativa de clique.", e);
        } catch (ElementNotInteractableException e) {
            throw new ElementNotInteractableException("O elemento não está interagível no momento do clique.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao tentar clicar no elemento.", e);
        }
    }

    public void clicarBtnSalvar(WebElement btnSave) {
        int explicitTimeout = Integer.parseInt(ConfigReader.getProperty("explicit.timeout"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeout));
        try {
            WebElement visibleButton = wait.until(ExpectedConditions.visibilityOf(btnSave));
            wait.until(ExpectedConditions.elementToBeClickable(visibleButton)).click();
            System.out.println("Clique realizado com sucesso no botão Salvar.");
        } catch (TimeoutException e) {
            throw new TimeoutException("O botão Salvar não ficou disponível para clique dentro do tempo limite.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("O elemento ficou obsoleto durante a tentativa de clique.", e);
        } catch (ElementClickInterceptedException e) {
            throw new RuntimeException("O clique no botão Salvar foi interceptado por outro elemento.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao tentar clicar no botão Salvar.", e);
        }
    }



    private boolean isElementInViewport(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (Boolean) jsExecutor.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (" +
                        "rect.top >= 0 && " +
                        "rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                        ");", element);
    }

    public void write(WebElement element, Object value) {
        try {

            WebElement visibleElement = waitUtils.waitForVisibility(element);
            waitUtils.waitForClickability(visibleElement);

            visibleElement.clear();
            if (value != null) {
                String valueAsString;
                if (value instanceof String) {
                    valueAsString = (String) value;
                } else if (value instanceof Integer) {
                    valueAsString = String.valueOf(value);
                } else if (value instanceof Double) {
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                    valueAsString = decimalFormat.format(value);
                } else {
                    throw new IllegalArgumentException("Tipo de valor inválido. Aceitos: String, Integer ou Double.");
                }
                visibleElement.sendKeys(valueAsString);
                System.out.println("Texto inserido com sucesso no elemento: " + element + " | Valor: " + valueAsString);
            } else {
                throw new IllegalArgumentException("O valor fornecido não pode ser nulo.");
            }
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

    public void selectDropdownOption(WebElement dropdown, Object value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
        WebElement visibleDropdown = wait.until(ExpectedConditions.visibilityOf(dropdown));
        wait.until(ExpectedConditions.elementToBeClickable(visibleDropdown));
        Select select = new Select(visibleDropdown);
        try {
            if (value != null) {
                List<WebElement> options = select.getOptions();
                boolean optionExists = false;
                for (WebElement option : options) {
                    String optionText = option.getText();
                    if (value instanceof String && optionText.equals(value)) {
                        optionExists = true;
                        select.selectByVisibleText((String) value);
                        System.out.println("Opção selecionada (texto visível): " + value);
                        break;
                    } else if (value instanceof Integer && option.getAttribute("index") != null &&
                            Integer.parseInt(Objects.requireNonNull(option.getAttribute("index"))) == (Integer) value) {
                        optionExists = true;
                        select.selectByIndex((Integer) value);
                        System.out.println("Opção selecionada (índice): " + value);
                        break;
                    } else if (value instanceof Double && optionText.equals(String.valueOf(value))) {
                        optionExists = true;
                        select.selectByVisibleText(String.valueOf(value));
                        System.out.println("Opção selecionada (texto convertido de double): " + value);
                        break;
                    }
                }
                if (!optionExists) {
                    throw new IllegalArgumentException("O valor fornecido não está disponível no dropdown: " + value);
                }
            } else {
                throw new IllegalArgumentException("O valor fornecido não pode ser nulo.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Não foi possível selecionar a opção.", e);
        }
    }


}


