package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;


public class HooverPlacingTest {

    private Hoover cut;

    @Before
    public void setup() {
        this.cut = new Hoover();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacement_illegalCoordsNull() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        cut.place(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacement_illegalCoordsX() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        cut.place(new Coords(-1, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacement_illegalCoordsY() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        cut.place(new Coords(0, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacement_illegalCoordsOutsideBoardBoundaryX() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        cut.place(new Coords(5, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacement_illegalCoordsOutsideBoardBoundaryY() {
        cut.initializeRoom(new Room(1, 1, new HashSet<>()));
        cut.place(new Coords(0, 5));
    }

    @Test(expected = IllegalStateException.class)
    public void testPlacement_illegalStateRoomNotInitialized() {
        cut.place(new Coords(0, 0));
    }

}