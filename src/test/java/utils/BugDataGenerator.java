package utils;

import java.util.Random;

public class BugDataGenerator {

    private static final String[] ACTION_VERBS = {"O sistema falha", "O aplicativo trava", "A tela exibe", "O botão não responde", "A função retorna"};
    private static final String[] DESCRIPTIONS = {
            "após clicar no botão de envio.",
            "quando o usuário tenta fazer login.",
            "durante a geração de relatórios.",
            "ao carregar a página principal.",
            "quando um arquivo grande é anexado."
    };
    private static final String[] STEPS = {
            "1. Abra o sistema.\n2. Clique no botão 'Enviar'.\n3. Observe a falha.",
            "1. Faça login com credenciais inválidas.\n2. Navegue até a aba 'Relatórios'.\n3. Tente gerar um relatório.",
            "1. Carregue um arquivo de tamanho superior a 10MB.\n2. Clique em 'Upload'.\n3. Aguarde o resultado.",
            "1. Acesse a tela inicial.\n2. Clique em 'Atualizar'.\n3. Note que a tela não responde.",
            "1. Faça login com usuário admin.\n2. Navegue até 'Configurações'.\n3. Clique em 'Salvar alterações'."
    };
    private static final String[] ADDITIONAL_INFO = {
            "Isso ocorre esporadicamente em ambientes de produção.",
            "O erro acontece apenas em navegadores específicos, como Firefox.",
            "O problema foi identificado na versão 1.2.3 do sistema.",
            "Nenhum log foi gerado durante o incidente.",
            "O comportamento esperado é que a página carregue sem erros."
    };

    private static final Random RANDOM = new Random();

    public static String generateSummary() {
        return ACTION_VERBS[RANDOM.nextInt(ACTION_VERBS.length)] + " " + DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)];
    }

    public static String generateDescription() {
        return "Descrição detalhada:\n" +
                ACTION_VERBS[RANDOM.nextInt(ACTION_VERBS.length)] + " " +
                DESCRIPTIONS[RANDOM.nextInt(DESCRIPTIONS.length)] +
                "\n\nImpacto: Usuários não conseguem prosseguir com a operação.";
    }

    public static String generateStepsToReproduce() {
        return "Passos para reproduzir:\n" + STEPS[RANDOM.nextInt(STEPS.length)];
    }

    public static String generateAdditionalInfo() {
        return "Informações adicionais:\n" + ADDITIONAL_INFO[RANDOM.nextInt(ADDITIONAL_INFO.length)];
    }
}

