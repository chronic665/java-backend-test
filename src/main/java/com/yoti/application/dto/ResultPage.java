package com.yoti.application.dto;

import com.google.common.base.MoreObjects;
import com.yoti.application.entity.Coords;

public class ResultPage {
    private final Coords position;
    private final int cleanedTiles;

    public ResultPage(final Coords position, final int cleanedTiles) {
        this.position = position;
        this.cleanedTiles = cleanedTiles;
    }

    public Coords getPosition() {
        return position;
    }

    public int getCleanedTiles() {
        return cleanedTiles;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("position", position)
                .add("cleanedTiles", cleanedTiles)
                .toString();
    }
}
