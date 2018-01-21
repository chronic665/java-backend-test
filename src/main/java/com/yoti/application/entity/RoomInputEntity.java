package com.yoti.application.entity;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Spring Data MongoDB does not support DataType "Queue"
 */
public class RoomInputEntity {

    private final Room room;
    private final Coords botCoords;
    private final List<Instruction> instructions;

    public RoomInputEntity(Room room, Coords botCoords, List<Instruction> instructions) {
        this.room = room;
        this.botCoords = botCoords;
        this.instructions = instructions;
    }

    public Room getRoom() {
        return room;
    }

    public Coords getBotCoords() {
        return botCoords;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("room", room)
                .add("botCoords", botCoords)
                .add("instructions", instructions)
                .toString();
    }
}
