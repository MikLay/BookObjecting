package com.company.entity.symbol;

import java.util.Objects;

public abstract class Symbol {

    private final char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Symbol)) {
            return false;
        }
        Symbol symbol1 = (Symbol) o;
        return getSymbol() == symbol1.getSymbol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol());
    }

    @Override
    public String toString() {
        return "" + getSymbol();
    }
}
