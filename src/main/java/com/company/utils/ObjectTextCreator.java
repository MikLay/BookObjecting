package com.company.utils;

import com.company.entity.Sentence;
import com.company.entity.SentenceMember;
import com.company.entity.punctuation.Ender;
import com.company.setting.LanguageSettings;
import com.company.setting.fabric.SentenceMemberFabric;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectTextCreator {

    private static final String DOUBLE_BACK_SLESH = "\\";
    private final LanguageSettings languageSettings;
    private final SentenceMemberFabric sentenceMemberFabric;

    public ObjectTextCreator(LanguageSettings languageSettings) {
        this.languageSettings = languageSettings;
        this.sentenceMemberFabric = new SentenceMemberFabric(languageSettings);
    }

    public List<Sentence> createObjectText(String text) {

        List<Sentence> sentences = new ArrayList<>();

        Matcher textMatcher = createSentenceRegex().matcher(text);


        while (textMatcher.find()) {
            Matcher wordMatcher = createWordRegex().matcher(textMatcher.group());

            List<SentenceMember> members = new ArrayList<>();
            while (wordMatcher.find()) {
                members.add(sentenceMemberFabric.getSentenceMember(wordMatcher.group()));

            }

            sentences.add(new Sentence(members, ((Ender) members.get(members.size() - 1)).getTypeOfEnder().toString()));
        }
        return sentences;
    }

    public Pattern createWordRegex() {
        StringBuilder regex = new StringBuilder();
        regex.append("([");
        languageSettings.getSentenceEnders()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        languageSettings.getSentenceDelimiters()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        regex.append("](?![\\w]))+|([");

        languageSettings.getSentenceEnders()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        languageSettings.getSentenceDelimiters()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        regex.append("]?[\\w");

        languageSettings.getAlphabet()
                .forEach(regex::append);


        regex.append("]+)+|[");

        languageSettings.getSentenceBrackets()
                .forEach(
                        s -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(s)
                );

        languageSettings.getSentenceDelimiters()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        regex.append("]|\\s+");

        return Pattern.compile(regex.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    }

    public Pattern createSentenceRegex() {
        StringBuilder regex = new StringBuilder();
        regex.append("([^");

        languageSettings.getSentenceEnders()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        regex.append("]|([");

        languageSettings.getSentenceEnders()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue()));


        regex.append("]+(?=([");

        languageSettings.getAlphabet()
                .forEach(regex::append);

        languageSettings.getSentenceDelimiters()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        languageSettings.getSentenceBrackets()
                .forEach(s -> regex
                        .append(DOUBLE_BACK_SLESH)
                        .append(s)
                );

        regex.append("]))))+([");

        languageSettings.getSentenceEnders()
                .forEach(
                        customPair -> regex
                                .append(DOUBLE_BACK_SLESH)
                                .append(customPair.getLeftValue())
                );

        regex.append("]+(?=\\s))");

        return Pattern.compile(regex.toString(), Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    }


    /*
    Matcher m = Pattern.compile("([.,:!?;\\[\\]](?!([\\w])))+|([.,:!'?;()\\[\\]]?[\\wA-Яа-я]+)+|[\\[\\]\"\\\\()]|\\s+").matcher(s);
    Matcher v = Pattern.compile("([^.;]|([;.]+(?=([А-Яа-яA-Za-z]))))+([.;]+(?=\\s))").matcher(text);
*/


}
