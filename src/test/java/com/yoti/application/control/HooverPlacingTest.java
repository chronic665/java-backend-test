package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HooverPlacingTest {

    private Hoover cut;

    @BeforeEach
    public void setup() {
        this.cut = new Hoover();
    }

    @Test
    public void testPlacement_illegalCoordsNull() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> cut.place(null));
    }

    @Test
    public void testPlacement_illegalCoordsX() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> cut.place(new Coords(-1, 0)));
    }

    @Test
    public void testPlacement_illegalCoordsY() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> cut.place(new Coords(0, -1)));
    }

    @Test
    public void testPlacement_illegalCoordsOutsideBoardBoundaryX() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> cut.place(new Coords(5, 0)));
    }

    @Test
    public void testPlacement_illegalCoordsOutsideBoardBoundaryY() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        assertThrows(IllegalArgumentException.class, () -> cut.place(new Coords(0, 5)));
    }

    @Test
    public void testPlacement_illegalStateRoomNotInitialized() {
        assertThrows(IllegalStateException.class, () -> cut.place(new Coords(0, 0)));
    }

}