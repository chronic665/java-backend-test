package com.yoti.application.dto;

import java.util.List;

public class RoomInputDTO {

    private int[] roomSize;
    private int[] coords;
    private List<Integer[]> patches;
    private String instructions;

    public int[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int[] roomSize) {
        this.roomSize = roomSize;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public List<Integer[]> getPatches() {
        return patches;
    }

    public void setPatches(List<Integer[]> patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("roomSize", roomSize)
                .add("coords", coords)
                .add("patches", patches)
                .add("instructions", instructions)
                .toString();
    }
}
