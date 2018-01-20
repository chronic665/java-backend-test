package com.yoti.application.entity;

import com.google.common.base.MoreObjects;

import java.util.Set;
import java.util.stream.Collectors;

public class Room {
    private final int dimensionX;
    private final int dimensionY;
    private final Set<Patch> patches;

    public Room(int dimensionX, int dimensionY, Set<Patch> patches) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.patches = patches;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public Set<Patch> getPatches() {
        return patches;
    }

    public boolean isDirty(Coords coords) {
        if(outOfBoundary(coords)) return false;
        return patches.parallelStream()
                .filter(patch ->
                        patch.getCoords().getX() == coords.getX() &&
                                patch.getCoords().getY() == coords.getY()
                )
                .collect(Collectors.toSet())
                .isEmpty();
    }

    public boolean outOfBoundary(Coords coords) {
        return coords.getX() > dimensionX || coords.getY() > dimensionY;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dimensionX", dimensionX)
                .add("dimensionY", dimensionY)
                .add("patches", patches)
                .toString();
    }
}
