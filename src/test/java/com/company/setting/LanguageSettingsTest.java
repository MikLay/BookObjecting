package com.company.setting;


import com.company.entity.punctuation.TypesOfDelimiter;
import com.company.entity.punctuation.TypesOfEnder;
import com.company.setting.entity.CustomPair;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class LanguageSettingsTest {

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

    );

    private List<String> brackets = asList(
            ")", "(", "[", "]", "\""
    );

    private LanguageSettings languageSettings = new LanguageSettings
            .LSBuilder()
            .withAlphabet(alphabet)
            .withDelimeters(delimiters)
            .withEnders(enders)
            .withBrackets(brackets).build();


    @Test
    public void ShouldBeTrueIsWord() {
        boolean actual = languageSettings.isWord("word");
        assertTrue(actual);
    }

    @Test
    public void ShouldBeFalseIsWord() {
        boolean actual = languageSettings.isWord(",");
        assertFalse(actual);
    }

    @Test
    public void ShouldBeFalseEmptyIsWord() {
        boolean actual = languageSettings.isWord("");
        assertFalse(actual);
    }

    @Test
    public void ShouldBeTrueIsEnder() {
        boolean actual = languageSettings.isEnder(".");
        assertTrue(actual);
    }

    @Test
    public void ShouldBeFalseIsEnder() {
        boolean actual = languageSettings.isEnder(".ama");
        assertFalse(actual);
    }


    @Test
    public void ShouldBeTrueIsBracket() {
        boolean actual = languageSettings.isBracket("[");
        assertTrue(actual);
    }

    @Test
    public void ShouldBeFalseIsBracket() {
        boolean actual = languageSettings.isBracket(".");
        assertFalse(actual);
    }

    @Test
    public void ShouldBeTrueIsDelimiter() {
        boolean actual = languageSettings.isDelimiter(",");
        assertTrue(actual);

    }

    @Test
    public void ShouldBeFalseIsDelimiter() {
        boolean actual = languageSettings.isDelimiter("...");
        assertFalse(actual);

    }

    @Test
    public void ShouldReturnAlphabetSize() {
        int actual = languageSettings.getAlphabet_size();
        int expected = alphabet.size();
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnTypeOfDelimeter() {
        TypesOfDelimiter actual = languageSettings.getTypeOfDelimeter(":");
        TypesOfDelimiter expected = TypesOfDelimiter.MARKER;
        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void ShouldThrowExceptionTypeOfDelimeter() {
        TypesOfDelimiter actual = languageSettings.getTypeOfDelimeter("2");
        TypesOfDelimiter expected = TypesOfDelimiter.MARKER;
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldReturnTypeOfEnder() {
        TypesOfEnder actual = languageSettings.getTypeOfEnder("?");
        TypesOfEnder expected = TypesOfEnder.INTERROGATIVE;
        assertEquals(expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void ShouldThrowExceptionTypeOfEnder() {
        TypesOfEnder actual = languageSettings.getTypeOfEnder("2");
    }
}
