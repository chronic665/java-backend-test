package com.yoti.application.entity;

import java.util.Objects;

public class Patch {
    private final Coords coords;

    public Patch(Coords coords) {
        this.coords = coords;
    }

    public Coords getCoords() {
        return coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patch patch = (Patch) o;
        return Objects.equals(coords, patch.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords);
    }

    @Override
    public String toString() {
        return "[" + coords.toString() + "]]";
    }
}
