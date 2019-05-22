package com.company.setting.fabric;

import com.company.entity.SentenceMember;
import com.company.entity.Word;
import com.company.entity.punctuation.Brackets;
import com.company.entity.punctuation.Delimiter;
import com.company.entity.punctuation.Ender;
import com.company.setting.LanguageSettings;


public class SentenceMemberFabric {

    private final LanguageSettings languageSettings;

    public SentenceMemberFabric(LanguageSettings languageSettings) {
        this.languageSettings = languageSettings;
    }

    public SentenceMember getSentenceMember(String sentenceMember) {


        if (languageSettings.isBracket(sentenceMember)) {
            return new Brackets(sentenceMember);
        }
        if (languageSettings.isDelimiter(sentenceMember)) {
            return new Delimiter(sentenceMember, languageSettings.getTypeOfDelimeter(sentenceMember));
        }


        if (languageSettings.isEnder(sentenceMember)) {

            return new Ender(sentenceMember, languageSettings.getTypeOfEnder(sentenceMember));
        }
        return new Word(sentenceMember);
    }
}
