package com.company.entity.punctuation;

import com.company.entity.SentenceMember;
import com.company.entity.symbol.Sign;
import com.company.entity.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Ender extends SentenceMember {

    private final TypesOfEnder typeOfEnder;

    public Ender(String memberContent, TypesOfEnder typeOfEnder) {
        super(memberContent);
        this.typeOfEnder = typeOfEnder;
    }

    @Override
    protected List<Symbol> scanString(String content) {
        List<Symbol> symbols = new ArrayList<>();
        content.chars().forEachOrdered(symbol -> symbols.add(new Sign((char) symbol)));
        return symbols;
    }

    public TypesOfEnder getTypeOfEnder() {
        return typeOfEnder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ender)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Ender ender = (Ender) o;
        return getTypeOfEnder() == ender.getTypeOfEnder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTypeOfEnder());
    }

    @Override
    public String toString() {
        return super.toString() + " Type of Ender: " + getTypeOfEnder().toString();
    }
}
