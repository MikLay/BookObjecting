package com.company.utils;

import com.company.setting.LanguageSettings;
import com.company.setting.entity.CustomPair;
import com.company.setting.fabric.SentenceMemberFabric;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ObjectTextCreatorTest {

    private List<Character> alphabet = asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'
            , 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'
            , 'w', 'x', 'y', 'z', 'а', 'б', 'в', 'г', 'д', 'е', 'ж'
            , 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с'
            , 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь'
            , 'э', 'ю', 'я'
    );

    private List<CustomPair> enders = asList(
            new CustomPair(".", "DECLARATIVE"),
            new CustomPair("...", "EXCLAMATORY"),
            new CustomPair(";", "DECLARATIVE"),
            new CustomPair("!", "IMPERATIVE"),
            new CustomPair("?", "INTERROGATIVE"),
            new CustomPair("!?!?", "EXCLAMATORY")
    );

    private List<CustomPair> delimiters = asList(
            new CustomPair(",", "DEVIDER"),
            new CustomPair(":", "MARKER"),
            new CustomPair("|", "MARKER"),
            new CustomPair("`", "MARKER"),
            new CustomPair("\'", "MARKER"),
            new CustomPair("@", "MARKER"),
            new CustomPair("+", "MARKER"),
            new CustomPair("=", "COMPLETIONER"),
            new CustomPair("#", "COMPLETIONER"),
            new CustomPair("^", "COMPLETIONER"),
            new CustomPair("&", "MARKER"),
            new CustomPair("%", "MARKER")

    );

    private List<String> brackets = asList(
            ")", "(", "[", "]", "\\\""
    );

    @Mock
    private LanguageSettings languageSettings;

    @Mock
    private SentenceMemberFabric sentenceMemberFabric;

    @InjectMocks
    private ObjectTextCreator objectTextCreator;

    @Test
    public void ShouldCreateWordRegex() {
        when(languageSettings.getSentenceEnders()).thenReturn(enders);
        when(languageSettings.getSentenceDelimiters()).thenReturn(delimiters);
        when(languageSettings.getSentenceBrackets()).thenReturn(brackets);
        when(languageSettings.getAlphabet()).thenReturn(alphabet);
        Pattern actual = objectTextCreator.createWordRegex();
        Pattern expected = Pattern.compile("([\\.\\...\\;\\!\\?\\!?!?\\,\\:\\|\\`\\'\\@\\+\\=\\#\\^\\&\\%](?![\\w]))+|([\\.\\...\\;\\!\\?\\!?!?\\,\\:\\|\\`\\'\\@\\+\\=\\#\\^\\&\\%]?[\\wabcdefghijklmnopqrstuvwxyzабвгдежзийклмнопрстуфхцчшщъыьэюя]+)+|[\\)\\(\\[\\]\\\\\"\\,\\:\\|\\`\\'\\@\\+\\=\\#\\^\\&\\%]|\\s+");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ShouldCreateSentenceRegex() {
        when(languageSettings.getSentenceEnders()).thenReturn(enders);
        when(languageSettings.getSentenceDelimiters()).thenReturn(delimiters);
        when(languageSettings.getSentenceBrackets()).thenReturn(brackets);
        when(languageSettings.getAlphabet()).thenReturn(alphabet);
        Pattern actual = objectTextCreator.createSentenceRegex();
        Pattern expected = Pattern.compile("([^\\.\\...\\;\\!\\?\\!?!?]|([\\.\\...\\;\\!\\?\\!?!?]+(?=([abcdefghijklmnopqrstuvwxyzабвгдежзийклмнопрстуфхцчшщъыьэюя\\,\\:\\|\\`\\'\\@\\+\\=\\#\\^\\&\\%\\)\\(\\[\\]\\\\\"]))))+([\\.\\...\\;\\!\\?\\!?!?]+(?=\\s))");
        assertEquals(expected.toString(), actual.toString());
    }


}