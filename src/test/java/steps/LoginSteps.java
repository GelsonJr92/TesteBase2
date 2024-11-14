package steps;

import config.ConfigReader;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.BasePage;
import pages.LoginPage;



public class LoginSteps extends BasePage {
    LoginPage loginPage;
    String username = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");
    String url = ConfigReader.getProperty("base.url");

    public LoginSteps() {
        super(Hooks.getDriver());
        this.loginPage = new LoginPage(driver);
    }

    @Dado("que o usuário está na página de login")
    public void que_o_usuario_esta_na_pagina_de_login() {
        navigateToBaseUrl(url);
    }

    @Quando("o usuário insere credenciais válidas")
    public void o_usuario_insere_credenciais_validas() {
        loginPage.logar(username, password);
    }

    @Então("o usuário deve ser redirecionado para o painel principal")
    public void o_usuario_deve_ser_redirecionado_para_o_painel_principal() {
        loginPage.validarTexto(loginPage.homeText,username);
    }

    @Quando("o usuário insere credenciais inválidas")
    public void o_usuário_insere_credenciais_invalidas() {
        loginPage.logar("teste", "123456789");
    }

    @Então("o usuário deve receber uma mensagem de erro")
    public void o_usuario_deve_receber_uma_mensagem_de_erro() {
    loginPage.validarTexto(loginPage.alertMessage,"Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.");
    }
}
