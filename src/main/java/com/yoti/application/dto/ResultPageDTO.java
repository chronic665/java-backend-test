package com.yoti.application.dto;

import com.google.common.base.MoreObjects;

public class ResultPageDTO {
    private final int[] coords;
    private final int patches;

    public ResultPageDTO(int[] coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("coords", coords)
                .add("patches", patches)
                .toString();
    }
}
