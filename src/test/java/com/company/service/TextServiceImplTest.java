package com.company.service;

import com.company.entity.Sentence;
import com.company.entity.SentenceMember;
import com.company.entity.TypesOfSentence;
import com.company.entity.Word;
import com.company.entity.punctuation.Delimiter;
import com.company.entity.punctuation.Ender;
import com.company.entity.punctuation.TypesOfDelimiter;
import com.company.entity.punctuation.TypesOfEnder;
import com.company.repository.TextRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TextServiceImplTest {

    private List<Sentence> sentences;

    @Mock
    private TextRepository textRepository;

    @InjectMocks
    private TextServiceImpl textService;

    @Before
    public void generateSentences() {
        sentences = asList(
                new Sentence(generateSentenceMembersHello(), TypesOfSentence.DECLARATIVE.toString()),
                new Sentence(generateSentenceMemberFine(), TypesOfSentence.INTERROGATIVE.toString()),
                new Sentence(generateSentenceMembersHello(), TypesOfSentence.DECLARATIVE.toString())
        );

    }

    private List<SentenceMember> generateSentenceMembersHello() {
        return asList(
                new Word("Hello"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("how"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("are"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("you"),
                new Delimiter(",", TypesOfDelimiter.DEVIDER),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("my"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("friend"),
                new Ender("?", TypesOfEnder.INTERROGATIVE),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER));
    }

    private List<SentenceMember> generateSentenceMemberFine() {
        return asList(
                new Word("Thank"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("you"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("I'am"),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER),
                new Word("fine"),
                new Ender(".", TypesOfEnder.INTERROGATIVE),
                new Delimiter(" ", TypesOfDelimiter.DEVIDER));

    }


    @Test
    public void ShouldGetText() {
        when(textRepository.getText()).thenReturn(sentences);
        List<Sentence> actual = textService.getText();
        List<Sentence> expected = sentences;
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldAddSentence() {
        Sentence sentence = new Sentence(generateSentenceMemberFine(), TypesOfSentence.DECLARATIVE.toString());
        when(textRepository.addSentence(sentence)).thenReturn(true);
        boolean actual = textService.addSentence(sentence);
        assertTrue(actual);
    }


    @Test
    public void removeSentence() {
        Sentence sentence = new Sentence(generateSentenceMemberFine(), TypesOfSentence.DECLARATIVE.toString());
        when(textRepository.removeSentence(sentence)).thenReturn(true);
        boolean actual = textService.removeSentence(sentence);
        assertTrue(actual);

    }

    @Test
    public void findSentencesByType() {
        List<Sentence> expected = new ArrayList<>();
        expected.add(sentences.get(0));
        expected.add(sentences.get(2));
        when(textRepository.getText()).thenReturn(sentences);
        List<Sentence> actual = textService.findSentencesByType(Collections.singletonList(TypesOfSentence.DECLARATIVE));
        assertEquals(expected, actual);
    }

    @Test
    public void findWordsByLength() {
        List<SentenceMember> expected = new ArrayList<>();
        expected.add(sentences.get(0).getSentenceMemberList().get(2));
        expected.add(sentences.get(0).getSentenceMemberList().get(4));
        expected.add(sentences.get(0).getSentenceMemberList().get(6));
        expected.add(sentences.get(1).getSentenceMemberList().get(2));
        expected.add(sentences.get(2).getSentenceMemberList().get(2));
        expected.add(sentences.get(2).getSentenceMemberList().get(4));
        expected.add(sentences.get(2).getSentenceMemberList().get(6));

        when(textRepository.getText()).thenReturn(sentences);
        List<SentenceMember> actual = textService.findWordsByLength(3);
        assertEquals(expected, actual);
    }

    @Test
    public void findSentenceByLength() {
        List<Sentence> expected = new ArrayList<>();
        expected.add(sentences.get(0));
        expected.add(sentences.get(2));
        when(textRepository.getText()).thenReturn(sentences);
        List<Sentence> actual = textService.findSentenceByLength(30);
        assertEquals(expected, actual);
    }

}
