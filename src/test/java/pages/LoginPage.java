package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseTest {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='username']")
    public WebElement usernameField;
    @FindBy(css = "input[name='password']")
    public WebElement passwordField;
    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    public WebElement loginButton;
    @FindBy(css = "font[color='red'] label")
    public WebElement alertMessage;
    @FindBy(css = ".page-title.txt-color-blueDark")
    public WebElement homeText;


    public void logar(String username, String password) {
        waitUtils.waitForVisibility(usernameField).sendKeys(username);
        waitUtils.waitForVisibility(passwordField).sendKeys(password);
        waitUtils.waitForClickability(loginButton).click();
    }
}
