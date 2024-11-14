package utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Random;
import java.time.Duration;
import static utils.DriverManager.driver;

public class DropdownUtils {
    private static final Random RANDOM = new Random();
    private static final int EXPLICIT_TIMEOUT = 10; // Timeout padrão de 10 segundos para a espera explícita.

    /**
     * Seleciona um valor aleatório em um dropdown com base no intervalo especificado.
     *
     * @param dropdown O elemento WebElement do dropdown.
     * @param range O número total de opções disponíveis no dropdown.
     * @param ignoreFirstOption Ignorar a primeira opção do dropdown (índice 0).
     */
    public static void selectRandomDropdownOption(WebElement dropdown, int range, boolean ignoreFirstOption) {
        if (range <= 0) {
            throw new IllegalArgumentException("O range deve ser maior que 0.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
        WebElement visibleDropdown = wait.until(ExpectedConditions.visibilityOf(dropdown));
        wait.until(ExpectedConditions.elementToBeClickable(visibleDropdown));
        Select select = new Select(visibleDropdown);

        int randomIndex;
        if (ignoreFirstOption && range > 1) {
            randomIndex = 1 + RANDOM.nextInt(range - 1); // Gera índice entre 1 e range-1
        } else {
            randomIndex = RANDOM.nextInt(range); // Gera índice entre 0 e range-1
        }

        select.selectByIndex(randomIndex);
        System.out.println("Opção selecionada (índice): " + randomIndex);
    }
}





