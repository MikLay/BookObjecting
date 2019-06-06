package com.company.entity.punctuation;

import com.company.entity.SentenceMember;
import com.company.entity.symbol.Sign;
import com.company.entity.symbol.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Delimiter extends SentenceMember {

    private final TypesOfDelimiter typesOfDelimiter;

    public Delimiter(String memberContent, TypesOfDelimiter typesOfDelimiter) {
        super(memberContent);
        this.typesOfDelimiter = typesOfDelimiter;
    }

    public TypesOfDelimiter getTypesOfDelimiter() {
        return typesOfDelimiter;
    }

    @Override
    protected List<Symbol> scanString(String content) {
        List<Symbol> symbols = new ArrayList<>();
        content.chars().forEachOrdered(symbol -> symbols.add(new Sign((char) symbol)));
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delimiter)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Delimiter delimiter = (Delimiter) o;
        return getTypesOfDelimiter() == delimiter.getTypesOfDelimiter();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTypesOfDelimiter());
    }

    @Override
    public String toString() {
        return super.toString() + " Type of Delimeter: " + getTypesOfDelimiter().toString();
    }
}
