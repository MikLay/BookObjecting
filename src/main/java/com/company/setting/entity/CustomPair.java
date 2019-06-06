package com.company.setting.entity;

public class CustomPair {
    private final String leftValue;
    private final String rightValue;

    public CustomPair(String leftValue, String rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }
}
