
# TesteBase2

## Descrição do Projeto  
Este projeto automatiza testes para a aplicação Mantis, utilizando uma stack moderna composta por **Selenium**, **Cucumber** e **Java**. Ele inclui geração de relatórios automáticos e execução modularizada para cenários de teste.

## Estrutura do Projeto  
O projeto está organizado da seguinte forma:

1. **Configurações Gerais:**  
   Diretório: `src/test/java/config`  
   Contém classes para leitura de propriedades e configurações iniciais:  
   - `Config.java`  
   - `ConfigReader.java`  

2. **Page Object Model (POM):**  
   Diretório: `src/test/java/pages`  
   Contém classes que encapsulam elementos e interações da interface:  
   - `BasePage.java`  
   - `LoginPage.java`  
   - `TarefaPage.java`  

3. **Runners:**  
   Diretório: `src/test/java/runners`  
   Configuração dos test runners:  
   - `TestRunner.java`  

4. **Steps:**  
   Diretório: `src/test/java/steps`  
   Implementação dos passos das features:  
   - `Hooks.java`  
   - `LoginSteps.java`  
   - `TarefaSteps.java`  

5. **Utilitários:**  
   Diretório: `src/test/java/utils`  
   Utilitários para geração de dados, manipulação de drivers e relatórios:  
   - `BugDataGenerator.java`  
   - `ConfigExtentReport.java`  
   - `DriverManager.java`  
   - `DropdownUtils.java`  
   - `WaitUtils.java`  

6. **Recursos:**  
   Diretório: `src/test/resources`  
   - `config.properties`: Configurações do projeto.  
   - `features/`: Cenários de teste escritos em Gherkin:  
     - `Login.feature`  
     - `Tarefa.feature`  

---

## Dependências  
O projeto utiliza as seguintes dependências principais, gerenciadas pelo Maven:

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.26.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.x</version>
    </dependency>
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.x</version>
    </dependency>
</dependencies>
```

## Execução dos Testes  

### 1. Pré-requisitos  
- **Java 11+** instalado e configurado no `PATH`.  
- **Maven** configurado para gerenciar as dependências.  
- Navegadores suportados instalados (ex.: Chrome).  

### 2. Formas de Execução  

#### Linha de Comando  
Execute o seguinte comando na raiz do projeto:  

```bash
mvn test
```

#### IDE (IntelliJ IDEA, Eclipse)  
Execute diretamente os test runners a partir do arquivo `TestRunner.java`.

#### Maven Wrapper (Sem Maven Instalado)  
Se preferir, utilize o Maven Wrapper:  

```bash
./mvnw test
```

## Relatórios de Execução  

Após cada execução, os relatórios são gerados automaticamente pelo **ExtentReports**.  
Você pode encontrá-los no diretório:  

```plaintext
/reports/
```

Os relatórios incluem:  
- Detalhes dos testes executados.  
- Status de cada teste.  
- Evidências (screenshots) de falhas.  

## Configuração  

As variáveis de ambiente do projeto, como a URL base, tempo de espera e navegador, podem ser ajustadas no arquivo:  

```plaintext
src/test/resources/config.properties
```

## Contato  

Em caso de dúvidas ou problemas, entre em contato com o desenvolvedor do projeto.

---

### Nota  
Para habilitar o ícone de "Copiar Código" em blocos de código no GitHub, utilize markdown com ```bash, ```xml ou qualquer linguagem, como mostrado acima.
