package com.company;

import com.company.exception.ReadException;
import com.company.repository.TextRepository;
import com.company.repository.TextRepositoryListImpl;
import com.company.service.TextService;
import com.company.service.TextServiceImpl;
import com.company.setting.LanguageSettings;
import com.company.setting.entity.CustomPair;
import com.company.ui.Ui;
import com.company.utils.FromFileFiller;
import com.company.utils.ObjectTextCreator;

import static java.util.Arrays.asList;

public class ConsoleApplication {
    public static void main(String[] args) {

        LanguageSettings languageSettings = new LanguageSettings
                .LSBuilder()
                .withBrackets(asList(
                        ")", "(", "[", "]", "\""
                )).withAlphabet(asList(
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'
                        , 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'
                        , 'w', 'x', 'y', 'z', 'а', 'б', 'в', 'г', 'д', 'е', 'ж'
                        , 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с'
                        , 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь'
                        , 'э', 'ю', 'я'
                )).withEnders(asList(
                        new CustomPair(".", "DECLARATIVE"),
                        new CustomPair("...", "EXCLAMATORY"),
                        new CustomPair(";", "DECLARATIVE"),
                        new CustomPair("!", "IMPERATIVE"),
                        new CustomPair("?", "INTERROGATIVE"),
                        new CustomPair("!?!?", "EXCLAMATORY")
                )).withDelimeters(asList(
                        new CustomPair(",", "DEVIDER"),
                        new CustomPair(":", "MARKER"),
                        new CustomPair("|", "MARKER"),
                        new CustomPair("\\", "MARKER"),
                        new CustomPair("`", "MARKER"),
                        new CustomPair("\'", "MARKER"),
                        new CustomPair("@", "MARKER"),
                        new CustomPair("+", "MARKER"),
                        new CustomPair("=", "COMPLETIONER"),
                        new CustomPair("#", "COMPLETIONER"),
                        new CustomPair("^", "COMPLETIONER"),
                        new CustomPair("&", "MARKER"),
                        new CustomPair("%", "MARKER")

                )).build();

        FromFileFiller fromFileFiller = new FromFileFiller("C:\\Users\\mixei\\Desktop\\EpamLessons\\GitProjects\\BookReader\\src\\main\\resources\\Blinof.txt");
        ObjectTextCreator objectTextCreator = new ObjectTextCreator(languageSettings);
        TextRepository textRepository;

        try {

            textRepository = new TextRepositoryListImpl(objectTextCreator.createObjectText(fromFileFiller.toRead()));
        } catch (ReadException e) {
            e.printStackTrace();
            return;
        }

        TextService textService = new TextServiceImpl(textRepository);
        Ui ui = new Ui(textService);
        ui.run();
    }
}
