package com.company.setting.fabric;

import com.company.entity.Word;
import com.company.entity.punctuation.*;
import com.company.setting.LanguageSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SentenceMemberFabricTest {

    @Mock
    private LanguageSettings languageSettings;

    @InjectMocks
    private SentenceMemberFabric sentenceMemberFabric;

    @Test
    public void ShouldGetSentenceMemberBracket() {
        String sentenceMember = "]";

        when(languageSettings.isBracket(sentenceMember)).thenReturn(true);

        Brackets actual = (Brackets) sentenceMemberFabric.getSentenceMember(sentenceMember);
        Brackets expected = new Brackets("]");
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldGetSentenceMemberDelimiter() {
        String sentenceMember = ":";

        when(languageSettings.isBracket(sentenceMember)).thenReturn(false);
        when(languageSettings.isDelimiter(sentenceMember)).thenReturn(true);
        when(languageSettings.getTypeOfDelimeter(sentenceMember)).thenReturn(TypesOfDelimiter.COMPLETIONER);

        Delimiter actual = (Delimiter) sentenceMemberFabric.getSentenceMember(sentenceMember);
        Delimiter expected = new Delimiter(sentenceMember, TypesOfDelimiter.COMPLETIONER);
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldGetSentenceMemberEnder() {
        String sentenceMember = ".";

        when(languageSettings.isBracket(sentenceMember)).thenReturn(false);
        when(languageSettings.isDelimiter(sentenceMember)).thenReturn(false);
        when(languageSettings.isEnder(sentenceMember)).thenReturn(true);
        when(languageSettings.getTypeOfEnder(sentenceMember)).thenReturn(TypesOfEnder.DECLARATIVE);

        Ender actual = (Ender) sentenceMemberFabric.getSentenceMember(sentenceMember);
        Ender expected = new Ender(sentenceMember, TypesOfEnder.DECLARATIVE);
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldGetSentenceMemberWord() {
        String sentenceMember = ".";

        when(languageSettings.isBracket(sentenceMember)).thenReturn(false);
        when(languageSettings.isDelimiter(sentenceMember)).thenReturn(false);
        when(languageSettings.isEnder(sentenceMember)).thenReturn(false);

        Word actual = (Word) sentenceMemberFabric.getSentenceMember(sentenceMember);
        Word expected = new Word(sentenceMember);
        assertEquals(expected, actual);
        assertEquals(expected.getClass().toString(), actual.getClass().toString());
    }


}
