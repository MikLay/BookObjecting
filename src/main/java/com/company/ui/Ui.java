package com.company.ui;

import com.company.entity.TypesOfSentence;
import com.company.service.TextService;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Ui {
    private final TextService textService;
    private final Scanner scanner = new Scanner(System.in);
    private ResourceBundle resourceBundle;

    public Ui(TextService textService) {
        scanner.useDelimiter("\n");
        this.textService = textService;
    }

    public void run() {
        boolean toContinue = true;
        int input;
        while (toContinue) {
            System.out.print("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n\n" +
                    "Українська   - 1\n" +
                    "Русский      - 2\n" +
                    "English      - 3\n" +
                    "Exit         - 0\n\n" +
                    "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
                    "*******************************************\n" +
                    "-> ");
            input = scanner.nextInt();
            toContinue = false;
            switch (input) {
                case 2:
                    Locale.setDefault(new Locale("ru", "RU"));
                    break;
                case 1:
                    Locale.setDefault(new Locale("uk", "UA"));
                    break;
                case 3:
                    Locale.setDefault(Locale.ENGLISH);
                    break;
                default:
                    toContinue = true;
                    System.out.println("!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!\n" +
                            resourceBundle.getString("error") +
                            "!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!");
            }
            resourceBundle = ResourceBundle.getBundle("BookReader");
        }

        toContinue = true;
        while (toContinue) {
            System.out.print("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n\n" +
                    resourceBundle.getString("operations_main") +
                    "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
                    "*******************************************\n" +
                    "-> ");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    printText();
                    break;
                case 2:
                    findInSentence();
                    break;
                case 3:
                    findSentenceByLength();
                    break;
                case 0:
                    toContinue = false;
                    break;
                default:
                    System.out.println("!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!\n" +
                            resourceBundle.getString("error") +
                            "!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!_!");
            }
        }


    }

    private void findSentenceByLength() {
        System.out.println("___________________________________________\n" +
                resourceBundle.getString("find_sentence") +
                "-------------------------------------------\n");
        System.out.print(resourceBundle.getString("length_of_sentence"));
        int n = scanner.nextInt();
        System.out.println(textService.findSentenceByLength(n));
    }

    private void findInSentence() {
        System.out.println("___________________________________________\n" +
                resourceBundle.getString("find_word") +
                "-------------------------------------------\n");
        System.out.println(resourceBundle.getString("length_of_word"));
        int n = scanner.nextInt();
        TypesOfSentence typesOfSentence = TypesOfSentence.UNKNOWN;

        boolean toContinue = true;
        while (toContinue) {
            toContinue = false;
            System.out.print(resourceBundle.getString("sentence_types"));
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    typesOfSentence = TypesOfSentence.DECLARATIVE;
                    break;
                case 2:
                    typesOfSentence = TypesOfSentence.INTERROGATIVE;
                    break;
                case 3:
                    typesOfSentence = TypesOfSentence.EXCLAMATORY;
                    break;
                case 4:
                    typesOfSentence = TypesOfSentence.IMPERATIVE;
                    break;
                default:
                    toContinue = true;
            }

        }
        System.out.println(textService.findWordsByLengthAndSentenceType(n, typesOfSentence).toString());

    }

    private void printText() {
        System.out.println("___________________________________________\n" +
                resourceBundle.getString("text") +
                "-------------------------------------------\n");
        System.out.println(textService.getText().toString());
    }
}
