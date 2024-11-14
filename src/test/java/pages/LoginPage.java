package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#username")
    public WebElement usernameField;
    @FindBy(css = "#password")
    public WebElement passwordField;
    @FindBy(css = "input[value='Entrar']")
    public WebElement loginButton;
    @FindBy(css = "div[class='alert alert-danger'] p")
    public WebElement alertMessage;
    @FindBy(css = "ul[class='breadcrumb'] li a")
    public WebElement homeText;


    public void logar(String username, String password) {
        waitUtils.waitForVisibility(usernameField).sendKeys(username);
        waitUtils.waitForClickability(loginButton).click();
        waitUtils.waitForVisibility(passwordField).sendKeys(password);
        waitUtils.waitForClickability(loginButton).click();
    }
}
