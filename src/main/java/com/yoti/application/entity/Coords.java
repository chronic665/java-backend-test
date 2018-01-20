package com.yoti.application.entity;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Coords {
    private final int x;
    private final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return x == coords.x &&
                y == coords.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return new StringBuilder("[")
                .append(x)
                .append("|")
                .append(y)
                .append("]")
                .toString();
    }
}
