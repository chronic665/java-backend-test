package com.yoti.application.entity;

public enum Instruction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");
    private final String literal;

    Instruction(final String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }

    public static Instruction forLiteral(String literal) {
        for (Instruction instruction : Instruction.values()) {
            if(instruction.literal.equals(literal)) return instruction;
        }
        throw new RuntimeException("Literal '" + literal + "' is no valid instruction");
    }
}
