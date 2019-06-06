package com.company.entity;

import com.company.entity.symbol.Alpha;
import com.company.entity.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

public final class Word extends SentenceMember {

    public Word(String memberContent) {
        super(memberContent);
    }

    @Override
    protected List<Symbol> scanString(String content) {
        List<Symbol> symbols = new ArrayList<>();
        content.chars().forEachOrdered(symbol -> symbols.add(new Alpha((char) symbol)));
        return symbols;
    }


}
