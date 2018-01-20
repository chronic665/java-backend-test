package com.yoti.application.control;

import com.yoti.application.dto.ResultPage;
import com.yoti.application.entity.RoomInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
public class CleaningService {

    @Autowired
    private Provider<Hoover> hoover;

    public ResultPage clean(RoomInput input) {
        Hoover hoover = this.hoover.get();
        hoover.initializeRoom(input.getRoom());
        hoover.place(input.getBotCoords());
        hoover.clean();
        return new ResultPage(hoover.getPosition(), hoover.getCleanedTiles());
    }
}
