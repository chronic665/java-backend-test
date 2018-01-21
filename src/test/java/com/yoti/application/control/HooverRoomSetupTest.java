package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class HooverRoomSetupTest {

    private Hoover cut;

    @Before
    public void setup() {
        this.cut = new Hoover();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomSetup_illegalXCoordinate() {
        Room illegalRoomXCoordinate = new Room(1, -1, new HashSet<>());
        cut.initializeRoom(illegalRoomXCoordinate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomSetup_illegalYCoordinate() {
        Room illegalRoomYCoordinate = new Room(-1, 1, new HashSet<>());
        cut.initializeRoom(illegalRoomYCoordinate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomSetup_0dimensionsAreinvalidY() {
        final Room room = new Room(1, 0, new HashSet<>());
        cut.initializeRoom(room);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomSetup_0dimensionsAreinvalidX() {
        final Room room = new Room(0, 1, new HashSet<>());
        cut.initializeRoom(room);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoomSetup_0dimensionsAreinvalidBoth() {
        final Room room = new Room(0, 0, new HashSet<>());
        cut.initializeRoom(room);
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
        Room room = new Room(1, 1, new HashSet<>());
        assertThat(cut.initializeRoom(room), is(false));
    }

    @Test
    public void testRoomSetup_dirtyFieldNeedsCleaning() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(0,0)));
        Room room = new Room(1, 1, patches);
        assertThat(cut.initializeRoom(room), is(true));
    }

    @Test
    public void testRoomSetup_outOfBounds() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        Room room = new Room(1, 1, patches);
        assertThat(cut.initializeRoom(room), is(false));
    }

    @Test
    public void testRoomSetup_dirtyFieldsOutsideBoundaryAreIgnored() {
        HashSet<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(5,5)));
        Room room = new Room(1, 2, patches);
        assertThat(cut.initializeRoom(room), is(false));
    }
}