package com.yoti.application.entity;


import com.google.common.base.MoreObjects;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoomInput {

    private final Room room;
    private final Coords botCoords;
    private final Queue<Instruction> instructions;

    private RoomInput(Room room, Coords botCoords, Queue<Instruction> instructions) {
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

    public Queue<Instruction> getInstructions() {
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

    public static class RoomInputBuilder {
        private Room roomToBuild;
        private Coords botCoordsToBuild;
        private List<Instruction> instructionsToBuild;

        public RoomInputBuilder withRoom(Room room) {
            this.roomToBuild = room;
            return this;
        }
        public RoomInputBuilder withBotAt(Coords coords) {
            this.botCoordsToBuild = coords;
            return this;
        }
        public RoomInputBuilder withInstructions(List<Instruction> instructions) {
            this.instructionsToBuild = instructions;
            return this;
        }
        public RoomInput build() {
            if(roomToBuild == null || botCoordsToBuild == null || instructionsToBuild == null)
                throw new RuntimeException("RoomBuilder was not given enough information to build a room");
            return new RoomInput(roomToBuild, botCoordsToBuild, new LinkedList<>(instructionsToBuild));
        }

    }
}
