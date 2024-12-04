package steps;

import config.ConfigReader;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.BaseTest;
import pages.ClientePage;
import pages.LoginPage;
import io.cucumber.datatable.DataTable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ClienteSteps extends BaseTest {
    private final ClientePage clientePage;
    private final LoginPage loginPage;
    private final String username = ConfigReader.getProperty("username");
    private final String password = ConfigReader.getProperty("password");
    private final String url = ConfigReader.getProperty("base.url");
    Actions actions = new Actions(driver);
    String nome;
    String cpf;
    String status;
    String saldoString;

    public ClienteSteps() {
        super(Hooks.getDriver());
        this.clientePage = new ClientePage(driver);
        this.loginPage = new LoginPage(driver);
    }

    @Dado("que o sistema está disponível")
    public void que_o_sistema_está_disponível() {
        navigateToBaseUrl(url+"/login");
        loginPage.logar(username, password);
    }

    @Dado("o usuário acessa a funcionalidade de Incluir Cliente")
    public void o_usuário_acessa_a_funcionalidade_de_incluir_cliente() {
    clicarElemento(clientePage.qaField);
    clicarElemento(clientePage.clientField);
    clicarElemento(clientePage.includeField);
    }

    @Quando("o usuário preenche os campos com Nome {string}, CPF {string}, Status {string} e Saldo Disponivel {double}")
    public void o_usuário_preenche_os_campos_obrigatórios_com_os_seguintes_dados(String nome, String cpf, String status, double saldo) {
        write(clientePage.nameField, nome);
        write(clientePage.cpfField, cpf);
        if (status.equals("Ativo")) {
            selectDropdownOption(clientePage.statusField, 0);
        } else if (status.equals("Inativo")){
            selectDropdownOption(clientePage.statusField, 1);
        }
        write(clientePage.balanceField, saldo);
    }


    @Quando("o usuário tenta preencher os campos obrigatórios sem o campo CPF")
    public void o_usuário_tenta_preencher_os_campos_obrigatórios_sem_o_campo_cpf(DataTable dataTable) {
        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);
        nome = dados.get(0).get("Nome");
        saldoString = dados.get(0).get("Saldo Disponivel");
        write(clientePage.nameField, nome);
        selectDropdownOption(clientePage.statusField,0);
        write(clientePage.balanceField, saldoString);
    }

    @Quando("o usuário tenta preencher os campos obrigatórios sem o campo Nome")
    public void o_usuário_tenta_preencher_os_campos_obrigatórios_sem_o_campo_nome(DataTable dataTable) {
        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);
        cpf = dados.get(0).get("CPF");
        saldoString = dados.get(0).get("Saldo Disponivel");
        status = dados.get(0).get("Status");
        write(clientePage.cpfField, cpf);
        if (status.equals("Ativo")) {
            selectDropdownOption(clientePage.statusField, 0);
        } else if (status.equals("Inativo")){
            selectDropdownOption(clientePage.statusField, 1);
        }
        write(clientePage.balanceField, saldoString);
    }

    @E("confirma o cadastro")
    public void confirma_o_cadastro() {
        clicarBtnSalvar(clientePage.btnSave);
//        actions.sendKeys("\t").sendKeys("\t").perform(); // Envia a tecla Tab duas vezes
//        actions.sendKeys(Keys.ENTER).perform();
    }

    @E("o sistema deve exibir a mensagem {string}")
    public void o_sistema_deve_exibir_a_mensagem(String string) {
        validarTexto(clientePage.messageField, string);
    }

    @Então("o sistema deve exibir a mensagem para informar o campo obrigatorio cpf {string}")
    public void o_sistema_deve_exibir_a_mensagem_de_campo_obrigatorio_cpf(String string) {
        validarTexto(clientePage.messageMandatoryFieldCpf, string);
    }

    @Então("o sistema deve exibir a mensagem para informar o campo obrigatorio nome {string}")
    public void o_sistema_deve_exibir_a_mensagem_de_campo_obrigatorio_nome(String string) {
        validarTexto(clientePage.messageMandatoryFieldName, string);
    }

    @Quando("o cliente {string} tenta realizar uma compra no valor de {double}")
    public void o_cliente_tenta_realizar_uma_compra_no_valor_de(String cliente, Double valor) {
        clicarElemento(clientePage.qaField);
        clicarElemento(clientePage.transactionField);
        clicarElemento(clientePage.includeSaleField);
        selectDropdownOption(clientePage.selectClientField, cliente);
        write(clientePage.balanceTransaction, valor);
    }

    @Então("a venda deve ser realizada com sucesso")
    public void a_venda_deve_ser_realizada_com_sucesso() {
        clicarElemento(clientePage.btnSave);
        validarTexto(clientePage.messageField, "Venda realizada com sucesso");
    }

    @Então("a venda não deve ser permitida")
    public void a_venda_não_deve_ser_permitida() {
        clicarElemento(clientePage.btnSave);
        validarTexto(clientePage.messageField, "Venda realizada com sucesso");
    }
}
