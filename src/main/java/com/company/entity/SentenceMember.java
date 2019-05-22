package com.company.entity;

import com.company.entity.symbol.Symbol;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class SentenceMember {

    private final List<Symbol> memberContent;

    protected SentenceMember(String memberContent) {
        this.memberContent = scanString(memberContent);
    }

    protected abstract List<Symbol> scanString(String content);

    public List<Symbol> getMemberContent() {
        return memberContent;
    }

    public String getMemberContentString() {
        return memberContent.stream().map(symbol -> symbol.getSymbol() + "").collect(Collectors.joining());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SentenceMember)) {
            return false;
        }
        SentenceMember that = (SentenceMember) o;
        return Objects.equals(getMemberContent(), that.getMemberContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberContent());
    }

    @Override
    public String toString() {
        return "\nSentenceMember{" +
                "memberContent=" + getMemberContentString() +
                '}';
    }
}
