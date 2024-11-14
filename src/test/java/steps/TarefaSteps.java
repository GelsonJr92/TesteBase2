package steps;

import config.ConfigReader;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.BasePage;
import pages.LoginPage;
import pages.TarefaPage;
import utils.BugDataGenerator;
import utils.DropdownUtils;


public class TarefaSteps extends BasePage {
    TarefaPage tarefaPage;

    public TarefaSteps() {
        super(Hooks.getDriver());
        this.loginPage = new LoginPage(driver);
        this.tarefaPage = new TarefaPage(driver);
    }

    private final LoginPage loginPage;
    String username = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");

    @Dado("que o usuário está na página de cadastro de tarefas")
    public void que_o_usuario_esta_na_pagina_de_cadastro_de_tarefas() {
        loginPage.logar(username, password);
    }

    @Quando("o usuário insere os dados da tarefa")
    public void o_usuario_insere_os_dados_da_tarefa() throws InterruptedException {
        loginPage.validarTexto(loginPage.homeText,username);
        clicarElemento(tarefaPage.createTaskField);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.categoryField,2, true);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.frequencyField, 6,false);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.severityField, 8,false);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.priorityField, 6, false);
        writeText(tarefaPage.summaryField, BugDataGenerator.generateSummary());
        writeText(tarefaPage.descriptionField, BugDataGenerator.generateDescription());
        writeText(tarefaPage.stepsToReproduceField, BugDataGenerator.generateStepsToReproduce());
        writeText(tarefaPage.additional_infoField, BugDataGenerator.generateAdditionalInfo());
        DropdownUtils.selectRandomDropdownOption(tarefaPage.tagSelectField,50,false);
        uploadFile(tarefaPage.attachFileField,"C:\\Users\\Pc\\Downloads\\arquivo_teste_mantis.txt");
        pressEnter();
    }

    @Quando("o usuário não preenche todos os campos obrigatórios")
    public void oUsuarioNaoPreencheTodosOsCamposObrigatarios() {
        loginPage.validarTexto(loginPage.homeText,username);
        clicarElemento(tarefaPage.createTaskField);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.categoryField,2, true);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.frequencyField, 6,false);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.severityField, 8,false);
        DropdownUtils.selectRandomDropdownOption(tarefaPage.priorityField, 6, false);
        writeText(tarefaPage.descriptionField, BugDataGenerator.generateDescription());
        writeText(tarefaPage.stepsToReproduceField, BugDataGenerator.generateStepsToReproduce());
        writeText(tarefaPage.additional_infoField, BugDataGenerator.generateAdditionalInfo());
        DropdownUtils.selectRandomDropdownOption(tarefaPage.tagSelectField,50,false);
        uploadFile(tarefaPage.attachFileField,"C:\\Users\\Pc\\Downloads\\arquivo_teste_mantis.txt");
        pressEnter();
    }

    @Quando("informa a visibilidade como privada")
    public void informa_a_visibilidade_como_privada() {
        clicarElemento(tarefaPage.checkBoxPrivateFileField);
    }

    @Quando("informa a visibilidade como publica")
    public void informa_a_visibilidade_como_publica() {
        clicarElemento(tarefaPage.checkBoxPublicFileField);
    }

    @Quando("clica no botão de cadastro")
    public void clica_no_botão_de_cadastro() {
        clicarElemento(tarefaPage.btnCreateNewTask);
    }

    @Então("a tarefa deve ser cadastrada com sucesso")
    public void a_tarefa_deve_ser_cadastrada_com_sucesso() {
        tarefaPage.validarTexto(tarefaPage.alert,"Operação realizada com sucesso.");
    }

    @Então("devo receber uma mensagem de erro no cadastro da tarefa")
    public void devoReceberUmaMensagemDeErroNoCadastroDaTarefa() throws InterruptedException {
       BasePage.validarMensagemDeErro(tarefaPage.summaryField,"Preencha este campo.");
       Thread.sleep(2000);
    }
}
