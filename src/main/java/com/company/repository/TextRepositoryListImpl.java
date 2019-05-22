package com.company.repository;

import com.company.entity.Sentence;

import java.util.List;

public class TextRepositoryListImpl implements TextRepository {

    private List<Sentence> sentences;

    public TextRepositoryListImpl(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public List<Sentence> getText() {
        return sentences;
    }

    @Override
    public boolean addSentence(Sentence sentence) {
        return sentences.add(sentence);
    }

    @Override
    public void addSentence(Sentence sentence, int indexOfSentence) {
        sentences.add(indexOfSentence, sentence);
    }

    @Override
    public boolean removeSentence(Sentence sentence) {
        return sentences.remove(sentence);
    }

    @Override
    public void removeSentence(int numberOfSentence) {
        sentences.remove(numberOfSentence);
    }
}
