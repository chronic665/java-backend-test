package com.yoti.application.entity;

import com.google.common.base.MoreObjects;

import java.util.Set;

public class Room {
    private final int dimensionX;
    private final int dimensiony;
    private final Set<Patch> patches;

    public Room(int dimensionX, int dimensiony, Set<Patch> patches) {
        this.dimensionX = dimensionX;
        this.dimensiony = dimensiony;
        this.patches = patches;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensiony() {
        return dimensiony;
    }

    public Set<Patch> getPatches() {
        return patches;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dimensionX", dimensionX)
                .add("dimensiony", dimensiony)
                .add("patches", patches)
                .toString();
    }
}
