package com.yoti.application.control;

import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Room;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Hoover {

    private Coords position;
    private int cleanedTiles;

    public void initializeRoom(Room room) {
        throw new RuntimeException("Not implemented yet!");
    }

    public void place(Coords botCoords) {
        throw new RuntimeException("Not implemented yet!");
    }

    public void clean() {
        throw new RuntimeException("Not implemented yet!");
    }

    public Coords getPosition() {
        return position;
    }

    public void setPosition(Coords position) {
        this.position = position;
    }

    public int getCleanedTiles() {
        return cleanedTiles;
    }

    public void setCleanedTiles(int cleanedTiles) {
        this.cleanedTiles = cleanedTiles;
    }
}
