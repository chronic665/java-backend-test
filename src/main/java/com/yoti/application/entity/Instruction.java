package com.yoti.application.entity;

public enum Instruction {
    NORTH("N", new Coords(0,1)),
    EAST ("E", new Coords(1,0)),
    SOUTH("S", new Coords(0, -1)),
    WEST ("W", new Coords(-1, 0));
    private final String literal;
    private final Coords coords;

    Instruction(final String literal, final Coords coords) {
        this.literal = literal;
        this.coords = coords;
    }

    public Coords getCoords() {
        return coords;
    }

    public static Instruction forLiteral(String literal) {
        for (Instruction instruction : Instruction.values()) {
            if(instruction.literal.equals(literal)) return instruction;
        }
        throw new RuntimeException("Literal '" + literal + "' is no valid instruction");
    }
}
