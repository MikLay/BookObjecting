package com.company.service;

import com.company.entity.Sentence;
import com.company.entity.SentenceMember;
import com.company.entity.TypesOfSentence;

import java.util.List;

public interface TextService {

    List<Sentence> getText();

    boolean addSentence(Sentence sentence);

    void addSentence(Sentence sentence, int numberOfSentence);

    boolean removeSentence(Sentence sentence);

    void removeSentence(int numberOfSentence);

    List<Sentence> findSentencesByType(List<TypesOfSentence> typeOfSentence);

    List<SentenceMember> findWordsByLength(int length);

    List<Sentence> findSentenceByLength(int length);

    List<SentenceMember> findWordsByLengthAndSentenceType(int n, TypesOfSentence typesOfSentence);
}
