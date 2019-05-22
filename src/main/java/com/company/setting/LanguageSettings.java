package com.company.setting;

import com.company.entity.punctuation.TypesOfDelimiter;
import com.company.entity.punctuation.TypesOfEnder;
import com.company.setting.entity.CustomPair;

import java.util.List;
import java.util.NoSuchElementException;

public class LanguageSettings {
    private List<CustomPair> sentenceDelimiters;
    private List<CustomPair> sentenceEnders;
    private List<String> sentenceBrackets;
    private List<Character> alphabet;

    private int alphabet_size;

    public boolean isWord(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (alphabet.contains(c))
                return true;
        }
        return false;
    }


    public boolean isEnder(String str) {
        return getSentenceEnders().stream().anyMatch(customPair -> customPair.getLeftValue().equals(str));
    }

    public boolean isBracket(String str) {
        return getSentenceBrackets().contains(str);
    }

    public boolean isDelimiter(String str) {
        return getSentenceDelimiters().stream().anyMatch(customPair -> customPair.getLeftValue().equals(str));
    }

    public TypesOfDelimiter getTypeOfDelimeter(String content) {
        String type = getSentenceDelimiters()
                .stream()
                .filter(
                        customPair -> customPair.
                                getLeftValue().
                                equals(content))
                .findFirst().orElseThrow(() -> new NoSuchElementException(content)).getRightValue();

        return TypesOfDelimiter.valueOf(type);
    }

    public TypesOfEnder getTypeOfEnder(String content) {
        String type = getSentenceEnders()
                .stream()
                .filter(
                        customPair -> customPair.
                                getLeftValue().
                                equals(content))
                .findFirst().orElseThrow(() -> new NoSuchElementException(content))
                .getRightValue();

        return TypesOfEnder.valueOf(type);
    }


    public List<String> getSentenceBrackets() {
        return sentenceBrackets;
    }


    private void setSentenceBrackets(List<String> sentenceBrackets) {
        this.sentenceBrackets = sentenceBrackets;
    }


    public List<CustomPair> getSentenceDelimiters() {
        return sentenceDelimiters;
    }

    private void setSentenceDelimiters(List<CustomPair> sentenceDelimiters) {
        this.sentenceDelimiters = sentenceDelimiters;
    }

    public List<CustomPair> getSentenceEnders() {
        return sentenceEnders;
    }

    private void setSentenceEnders(List<CustomPair> sentenceEnders) {
        this.sentenceEnders = sentenceEnders;
    }


    public List<Character> getAlphabet() {
        return alphabet;
    }

    private void setAlphabet(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public int getAlphabet_size() {
        return alphabet_size;
    }

    private void setAlphabet_size(int alphabet_size) {
        this.alphabet_size = alphabet_size;
    }


    public static class LSBuilder {
        private LanguageSettings currentlanguageSettings;

        public LSBuilder() {
            this.currentlanguageSettings = new LanguageSettings();
        }

        public LSBuilder withDelimeters(List<CustomPair> delimiters) {
            currentlanguageSettings.setSentenceDelimiters(delimiters);
            return this;
        }

        public LSBuilder withEnders(List<CustomPair> enders) {
            currentlanguageSettings.setSentenceEnders(enders);
            return this;
        }

        public LSBuilder withAlphabet(List<Character> alphabet) {
            currentlanguageSettings.setAlphabet(alphabet);
            currentlanguageSettings.setAlphabet_size(alphabet.size());
            return this;
        }

        public LSBuilder withBrackets(List<String> parable) {
            currentlanguageSettings.setSentenceBrackets(parable);
            return this;
        }

        public LanguageSettings build() {
            return currentlanguageSettings;
        }
    }

}
