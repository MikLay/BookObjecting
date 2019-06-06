package com.company.entity.punctuation;

import com.company.entity.SentenceMember;
import com.company.entity.symbol.Sign;
import com.company.entity.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Brackets extends SentenceMember {
    public Brackets(String memberContent) {
        super(memberContent);
    }

    @Override
    protected List<Symbol> scanString(String content) {
        List<Symbol> symbols = new ArrayList<>();
        content.chars().forEachOrdered(symbol -> symbols.add(new Sign((char) symbol)));
        return symbols;
    }

}
