package com.example.SpringBoot.Model.enums;

public enum InstrumentsEventType {
    ADD("ADD"),
    DELETE("DELETE")
    ;

    private final String text;

    InstrumentsEventType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}