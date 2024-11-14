package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TarefaPage extends BasePage{

    public TarefaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='Criar Tarefa']")
    public WebElement createTaskField;

    @FindBy(css = "#category_id")
    public WebElement categoryField;

    @FindBy(css = "#reproducibility")
    public WebElement frequencyField;

    @FindBy(css = "#severity")
    public WebElement severityField;

    @FindBy(css = "#priority")
    public WebElement priorityField;

    @FindBy(css = "i[title='+']")
    public WebElement selectProfileField;

    @FindBy(css = "#summary")
    public WebElement summaryField;

    @FindBy(css = "#description")
    public WebElement descriptionField;

    @FindBy(css = "#steps_to_reproduce")
    public WebElement stepsToReproduceField;

    @FindBy(css = "#additional_info")
    public WebElement additional_infoField;

    @FindBy(css = "#tag_select")
    public WebElement tagSelectField;


    @FindBy(css = "input[type='file']")
    public WebElement attachFileField;

    @FindBy(xpath = "//span[normalize-space()='público']")
    public WebElement checkBoxPublicFileField;

    @FindBy(xpath = "//span[normalize-space()='privado']")
    public WebElement checkBoxPrivateFileField;

    @FindBy(css = "input[value='Criar Nova Tarefa']")
    public WebElement btnCreateNewTask;

    @FindBy(css = ".alert.alert-success")
    public WebElement alert;
}
