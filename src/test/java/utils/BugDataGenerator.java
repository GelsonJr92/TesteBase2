package utils;

import com.github.javafaker.Faker;

public class BugDataGenerator {

    private static final Faker faker = new Faker();

    public static String generateSummary() {
        return faker.lorem().sentence();
    }

    public static String generateDescription() {
        return "Descrição detalhada:\n" +
                faker.lorem().sentence() +
                "\n\nImpacto: " + faker.company().catchPhrase() + ".";
    }

    public static String generateStepsToReproduce() {
        return "Passos para reproduzir:\n" +
                "1. " + faker.hacker().verb() + " " + faker.hacker().noun() + ".\n" +
                "2. " + faker.hacker().verb() + " " + faker.hacker().adjective() + " " + faker.hacker().noun() + ".\n" +
                "3. Observe que " + faker.hacker().verb() + ".";
    }

    public static String generateAdditionalInfo() {
        return "Informações adicionais:\n" +
                faker.chuckNorris().fact() +
                "\nComportamento esperado: " + faker.lorem().sentence() + ".";
    }
}
