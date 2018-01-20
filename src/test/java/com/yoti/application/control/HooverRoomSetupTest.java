package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testRoomSetup_nullPatchSetNeedsNoCleaning() {
        Room nullPatchRoom = new Room(1, 1, null);
        assertThat(cut.initializeRoom(nullPatchRoom), is(false));
    }

    @Test
    public void testRoomSetup_badPatchEntriesAreIgnored() {
        HashSet<Patch> nullPatches = new HashSet<>();
        nullPatches.add(null);
        Room nullPatchRoom = new Room(1, 1, nullPatches);
        assertThat(cut.initializeRoom(nullPatchRoom), is(false));

        HashSet<Patch> nullCoords = new HashSet<>();
        nullCoords.add(new Patch(null));
        Room nullCoordsRoom = new Room(1, 1, nullCoords);
        assertThat(cut.initializeRoom(nullCoordsRoom), is(false));
    }

    @Test
    public void testRoomSetup_EmptyRoomDoesntNeedCleaning() {
        Room room = new Room(1, 1, new HashSet<>());
        assertThat(cut.initializeRoom(room), is(false));
    }

    @Test
    public void testRoomSetup_onFieldRoomEmptyDoesntNeedCleaning() {
        Room room = new Room(0, 0, new HashSet<>());
        assertThat(cut.initializeRoom(room), is(false));
    }

    @Test
    public void testRoomSetup_dirtyFieldNeedsCleaning() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        Room room = new Room(1, 1, patches);
        assertThat(cut.initializeRoom(room), is(true));
    }

    @Test
    public void testRoomSetup_dirtyFieldsOutsideBoundaryAreIgnored() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        Room room = new Room(0, 0, patches);
        assertThat(cut.initializeRoom(room), is(false));
    }
}