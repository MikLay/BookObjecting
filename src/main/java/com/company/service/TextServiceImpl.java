package com.company.service;

import com.company.entity.Sentence;
import com.company.entity.SentenceMember;
import com.company.entity.TypesOfSentence;
import com.company.entity.Word;
import com.company.repository.TextRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private TextRepository textRepository;

    public TextServiceImpl(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public List<Sentence> getText() {
        return textRepository.getText();
    }

    @Override
    public boolean addSentence(Sentence sentence) {
        return textRepository.addSentence(sentence);
    }

    @Override
    public void addSentence(Sentence sentence, int numberOfSentence) {
        textRepository.addSentence(sentence, numberOfSentence);
    }

    @Override
    public boolean removeSentence(Sentence sentence) {
        return textRepository.removeSentence(sentence);
    }

    @Override
    public void removeSentence(int numberOfSentence) {
        textRepository.removeSentence(numberOfSentence);
    }

    @Override
    public List<Sentence> findSentencesByType(List<TypesOfSentence> typeOfSentence) {
        return textRepository.getText()
                .stream()
                .filter(sentence -> typeOfSentence.contains(sentence.getTypeOfSentence()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SentenceMember> findWordsByLength(int length) {
        return textRepository
                .getText()
                .stream()
                .map(Sentence::getSentenceMemberList)
                .flatMap(Collection::stream)
                .filter(
                        sentenceMember -> sentenceMember
                                .getMemberContent()
                                .size() == length
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Sentence> findSentenceByLength(int length) {
        return textRepository
                .getText()
                .stream()
                .filter(sentence ->
                        sentence.getSentenceMemberList()
                                .stream()
                                .mapToInt(sentenceMember ->
                                        sentenceMember.getMemberContent().size()).sum() == length)
                .collect(Collectors.toList());
    }

    @Override
    public List<SentenceMember> findWordsByLengthAndSentenceType(int n, TypesOfSentence typesOfSentence) {
        List<SentenceMember> words = new ArrayList<>();

        textRepository.getText()
                .stream()
                .filter(sentence ->
                        sentence.getTypeOfSentence()
                                .equals(typesOfSentence))
                .forEach(sentence ->
                        sentence.getSentenceMemberList()
                                .stream()
                                .filter(sentenceMember ->
                                        sentenceMember.getMemberContentString().length() == n && sentenceMember.getClass().equals(Word.class))
                                .forEach(sentenceMember ->
                                {
                                    if (!words.contains(sentenceMember)) {
                                        words.add(sentenceMember);
                                    }

                                }));

        return words;

    }


}
