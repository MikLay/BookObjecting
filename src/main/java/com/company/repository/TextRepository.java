package com.company.repository;

import com.company.entity.Sentence;

import java.util.List;

public interface TextRepository {
    List<Sentence> getText();

    boolean addSentence(Sentence sentence);

    void addSentence(Sentence sentence, int numberOfSentence);

    boolean removeSentence(Sentence sentence);

    void removeSentence(int numberOfSentence);
}
