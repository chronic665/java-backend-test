package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.yoti.application.entity.Instruction.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class HooverCleaningTest {

    private Hoover cut;

    @Before
    public void setup() {
        this.cut = new Hoover();
    }

    @Test(expected = IllegalStateException.class)
    public void roomNotInitializedThrowException() {
        cut.clean(new LinkedList<>(Collections.emptyList()));
    }

    @Test(expected = IllegalStateException.class)
    public void botNotPlacedThrowException() {
        initCleanRoom();
        cut.clean(new LinkedList<>(Collections.emptyList()));
    }

    private void initCleanRoom() {
        cut.initializeRoom(new Room(1,1, new HashSet<>()));
    }

    @Test
    public void emptyInstructions() {
        initCleanRoom();
        cut.place(new Coords(0,0));
        Coords before = cut.getPosition();
        cut.clean(new LinkedList<>(Collections.emptyList()));
        assertThat(cut.getPosition(), is(before));
        assertThat(cut.getCleanedTiles(), is(0));
    }

    /*
        {
          "roomSize" : [5, 5],
          "coords" : [1, 2],
          "patches" : [
            [1, 0],
            [2, 2],
            [2, 3]
          ],
          "instructions" : "NNESEESWNWW"
        }
     */
    @Test
    public void testExampleInput() {
        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,0)));
        patches.add(new Patch(new Coords(2,2)));
        patches.add(new Patch(new Coords(2,3)));
        Room room = new Room(5,5, patches);
        cut.initializeRoom(room);
        cut.place(new Coords(1,2));

        cut.clean(new LinkedList<>(Arrays.asList(NORTH, NORTH, EAST, SOUTH, EAST, EAST, SOUTH, WEST, NORTH, WEST, WEST)));

        assertThat(cut.getCleanedTiles(), is(1));
        assertThat(cut.getPosition(), is(new Coords(1,3)));
    }

    /*
        {
          "roomSize" : [5, 5],
          "coords" : [2, 1],
          "patches" : [
            [3, 0],
            [4, 1]
          ],
          "instructions" : "SSESNEESNN"
        }
     */
    @Test
    public void testBouncingFromWalls() {
        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(3,0)));
        patches.add(new Patch(new Coords(4,1)));
        Room room = new Room(5,5, patches);
        cut.initializeRoom(room);
        cut.place(new Coords(2,1));

        cut.clean(new LinkedList<>(Arrays.asList(SOUTH, SOUTH, EAST, SOUTH, NORTH, EAST, EAST, SOUTH, NORTH, NORTH)));

        assertThat(cut.getCleanedTiles(), is(2));
        assertThat(cut.getPosition(), is(new Coords(4,2)));
    }

    @Test
    public void testEmptyInstructions() {
        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(3,0)));
        patches.add(new Patch(new Coords(4,1)));
        Room room = new Room(5,5, patches);
        cut.initializeRoom(room);
        cut.place(new Coords(2,1));

        cut.clean(new LinkedList<>());

        assertThat(cut.getCleanedTiles(), is(0));
        assertThat(cut.getPosition(), is(new Coords(2,1)));
    }

    @Test
    public void testOnlyDirtyTileIsAtStart() {
        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(0,0)));
        Room room = new Room(1,2, patches);
        cut.initializeRoom(room);
        cut.place(new Coords(0,0));

        cut.clean(new LinkedList<>(Arrays.asList(NORTH)));

        assertThat(cut.getCleanedTiles(), is(1));
        assertThat(cut.getPosition(), is(new Coords(0,1)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullInstructionsRaiseException() {
        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(0,0)));
        Room room = new Room(1,2, patches);
        cut.initializeRoom(room);
        cut.place(new Coords(0,0));

        cut.clean(null);
    }


}