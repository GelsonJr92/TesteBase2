# TesteBase2
# Descrição do Projeto
# Este projeto automatiza testes para a aplicação Mantis, utilizando uma stack moderna composta por Selenium, Cucumber e Java. Ele inclui geração de relatórios automáticos e execução modularizada para cenários de teste.

# Estrutura do Projeto
src/test/java/config: Classes para configuração geral do projeto, como leitura de propriedades e configurações iniciais.

Config.java
ConfigReader.java
src/test/java/pages: Classes de Page Object Model para encapsular elementos e interações da interface.

BasePage.java
LoginPage.java
TarefaPage.java
src/test/java/runners: Configuração dos test runners.

TestRunner.java
src/test/java/steps: Implementação dos passos das features.

Hooks.java
LoginSteps.java
TarefaSteps.java
src/test/java/utils: Utilitários para suportar ações como geração de dados, manipulação de drivers e relatórios.

BugDataGenerator.java
ConfigExtentReport.java
DriverManager.java
DropdownUtils.java
WaitUtils.java
src/test/resources: Arquivos de configuração e cenários de teste.

config.properties
features/: Cenários em Gherkin.
Login.feature
Tarefa.feature
Dependências
# Este projeto utiliza as seguintes dependências principais, gerenciadas pelo Maven:

selenium-java: 4.26.0
cucumber-java: 7.x
extent-reports: Para geração de relatórios de execução.
# Execução dos Testes
Pré-requisitos
Java 11+ instalado e configurado no PATH.
Maven configurado para gerenciar as dependências.
Navegadores suportados para execução dos testes (ex.: Chrome).
# Formas de Execução
Linha de Comando:
Execute o seguinte comando na raiz do projeto:
mvn test
IDE (IntelliJ IDEA, Eclipse):
Rode diretamente os test runners a partir do arquivo TestRunner.java.

Terminal com Maven Wrapper (sem instalação de Maven):
./mvnw test

# Relatórios de Execução
Ao fim de cada execução, os relatórios são gerados automaticamente pelo ExtentReports.
Os relatórios podem ser encontrados no diretório:
# /reports/
# O arquivo gerado inclui informações detalhadas sobre cada teste executado, status, e evidências (screenshots) de falhas.

# Configuração
O arquivo config.properties permite configurar variáveis como URL base da aplicação, tempo de espera, e navegador utilizado.
Contato
Em caso de dúvidas ou problemas, entre em contato com o desenvolvedor do projeto. 
