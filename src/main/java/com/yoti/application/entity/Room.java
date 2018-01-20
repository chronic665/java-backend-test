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

    /**
     * Checks if a given coordinate is dirty. Requests for tiles outside the room borders are responded as "clean"
     * @param coords
     * @return
     */
    public boolean isDirty(Coords coords) {
        if(outOfBoundary(coords)) return false;
        long dirtyTileOnThisPosition = patches.parallelStream()
                .filter(patch ->
                        patch.getCoords().getX() == coords.getX() &&
                                patch.getCoords().getY() == coords.getY()
                )
                .count();
        return dirtyTileOnThisPosition > 0;
    }

    /**
     * Checks if the given coordinates are inside the room
     * @param coords
     * @return
     */
    public boolean outOfBoundary(Coords coords) {
        return coords.getX() < 0 || coords.getY() < 0 ||
                coords.getX() >= dimensionX || coords.getY() >= dimensionY;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dimensionX", dimensionX)
                .add("dimensionY", dimensionY)
                .add("patches", patches)
                .toString();
    }

    /**
     * Cleans a tile in the room
     * @param position
     */
    public void cleanTile(final Coords position) {
        this.patches.remove(new Patch(position));
    }
}
