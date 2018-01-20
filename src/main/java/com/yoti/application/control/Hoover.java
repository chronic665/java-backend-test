package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Instruction;
import com.yoti.application.entity.Room;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Hoover {

    private Coords position;
    private int cleanedTiles;
    private Room room;

    /**
     * Let's the Hoover scan the room and see if the room needs cleaning. If no dirty tiles are found the Hoover will say so.
     * You can let him go clean anyway, he's your robot and doesn't have free will.
     * @param room
     * @return boolean Does the room need cleaning?
     */
    public boolean initializeRoom(final Room room) {
        if(room == null || room.getDimensionX() < 0 || room.getDimensionY() < 0) {
            throw new IllegalArgumentException("No valid room layout has been provided to the Hoover!");
        }
        this.room = room;
        if(room.getPatches() == null) {
            return false;
        }
        long dirtyTiles = room.getPatches().parallelStream()
                // filter empty tiles
                .filter(patch -> patch != null && patch.getCoords() != null)
                // filter tiles outside of the rooms boundary
                .filter(patch -> !room.outOfBoundary(patch.getCoords()))
                .count();
        if(dirtyTiles == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void place(final Coords botCoords) {
        if(room == null) {
            throw new IllegalStateException("Room has not been initialized!");
        }
        if(botCoords == null || room.outOfBoundary(botCoords)) {
            throw new IllegalArgumentException("Hoover was placed outside the grid!");
        }
        this.position = botCoords;
    }

    public void clean(Queue<Instruction> instructions) {
        while(!instructions.isEmpty()) {
            final Instruction instruction = instructions.poll();
            final Coords destination = this.position.calculateCoords(instruction.getCoords());
            boolean moved = this.moveTo(destination);
            // we only really need to clean if we moved
            if(moved) {
                this.cleanTile();
            }
        }
    }

    private void cleanTile() {
        this.room.getPatches().remove(this.position);
        this.cleanedTiles++;
    }

    private boolean moveTo(Coords destination) {
        if(!room.outOfBoundary(destination)) {
            this.position = destination;
            return true;
        }
        return false;
    }

    public Coords getPosition() {
        return position;
    }


    public int getCleanedTiles() {
        return cleanedTiles;
    }

}
