package com.company.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Sentence {
    private final List<SentenceMember> sentenceMemberList;
    private final TypesOfSentence typeOfSentence;


    public Sentence(List<SentenceMember> sentenceMemberList, String typeOfSentence) {
        this.sentenceMemberList = sentenceMemberList;
        if (Arrays.stream(TypesOfSentence.values()).anyMatch(typesOfSentence -> typesOfSentence.toString().equals(typeOfSentence))) {
            this.typeOfSentence = TypesOfSentence.valueOf(typeOfSentence);
        } else {
            this.typeOfSentence = TypesOfSentence.UNKNOWN;
        }
    }

    public List<SentenceMember> getSentenceMemberList() {
        return sentenceMemberList;
    }

    public TypesOfSentence getTypeOfSentence() {
        return typeOfSentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sentence)) {
            return false;
        }
        Sentence sentence = (Sentence) o;
        return Objects.equals(getSentenceMemberList(), sentence.getSentenceMemberList()) &&
                getTypeOfSentence() == sentence.getTypeOfSentence();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSentenceMemberList(), getTypeOfSentence());
    }

    @Override
    public String toString() {
        return "\nSentence Type: " + getTypeOfSentence().toString()
                + " Sentence members: " + sentenceMemberList.toString();
    }
}
