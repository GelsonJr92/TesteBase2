package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientePage extends BaseTest {
    public ClientePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[title='QA']")
    public WebElement qaField;

    @FindBy(css = "a[title='Clientes']")
    public WebElement clientField;

    @FindBy(css = "#nome")
    public WebElement nameField;

    @FindBy(css = "#cpf")
    public WebElement cpfField;

    @FindBy(css = "#status")
    public WebElement statusField;

    @FindBy(css = "#cliente")
    public WebElement selectClientField;

    @FindBy(css = "#saldoCliente")
    public WebElement balanceField;

    @FindBy(css = "#valorTransacao")
    public WebElement balanceTransaction;


    @FindBy(css = "#botaoLimpar")
    public WebElement btnClean;

    @FindBy(css = "#botaoSalvar")
    public WebElement btnSave;

    @FindBy(xpath = "//a[normalize-space()='Cancelar']")
    public WebElement btnCancel;

    @FindBy(css = "a[title='Transações']")
    public WebElement transactionField;

    @FindBy(css = "a[href='/desafioqa/incluirVenda']")
    public WebElement includeSaleField;

    @FindBy(css = "a[title='Incluir']")
    public WebElement includeField;

    @FindBy(css = "div[id='alertMessage'] strong")
    public WebElement messageField;

    @FindBy(css = "small[data-bv-for='cpf']")
    public WebElement messageMandatoryFieldCpf;

    @FindBy(css = "small[data-bv-for='nome']")
    public WebElement messageMandatoryFieldName;





}
