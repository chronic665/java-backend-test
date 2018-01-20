package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class HooverRoomSetupTest {

    private Hoover cut;

    @BeforeEach
    public void setup() {
        this.cut = new Hoover();
    }

    @Test
    public void testRoomSetup_illegalXCoordinate() {
        Room illegalRoomXCoordinate = new Room(1, -1, new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> cut.initializeRoom(illegalRoomXCoordinate));
    }

    @Test
    public void testRoomSetup_illegalYCoordinate() {
        Room illegalRoomYCoordinate = new Room(-1, 1, new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> cut.initializeRoom(illegalRoomYCoordinate));
    }

    @Test
    public void testRoomSetup_nullPatchSet() {
        Room illegalRoomNoPatches = new Room(1, 1, null);
        assertThrows(IllegalArgumentException.class, () -> cut.initializeRoom(illegalRoomNoPatches));
    }

    @Test
    public void testRoomSetup_cleanFieldIsValid() {
        Room room = new Room(1, 1, new HashSet<>());
        cut.initializeRoom(room);
    }

    @Test
    public void testRoomSetup_onFieldRoomIsValid() {
        Room room = new Room(0, 0, new HashSet<>());
        cut.initializeRoom(room);
    }

    @Test
    public void testRoomSetup_dirtyFieldIsValid() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        Room room = new Room(1, 1, patches);
        cut.initializeRoom(room);
    }

    @Test
    public void testRoomSetup_nullPatchEntriesAreInvalid() {
        HashSet<Patch> nullPatches = new HashSet<>();
        nullPatches.add(null);
        Room nullPatchRoom = new Room(1, 1, nullPatches);
        assertThrows(IllegalArgumentException.class, () -> cut.initializeRoom(nullPatchRoom));

        HashSet<Patch> nullCoords = new HashSet<>();
        nullCoords.add(new Patch(null));
        Room nullCoordsRoom = new Room(1, 1, nullCoords);
        cut.initializeRoom(nullCoordsRoom);
    }

}