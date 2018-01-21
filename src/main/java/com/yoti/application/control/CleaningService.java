package com.yoti.application.control;

import com.yoti.application.config.PersistenceConfig;
import com.yoti.application.dto.ResultPage;
import com.yoti.application.entity.RoomCleaning;
import com.yoti.application.entity.RoomInput;
import com.yoti.application.repo.RoomCleaningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.Arrays;
import java.util.Date;

@Service
public class CleaningService {

    private final Provider<Hoover> hooverProvider;
    private final Provider<RoomCleaningRepository> repositoryProvider;
    private final Environment environment;

    @Autowired
    public CleaningService(final Provider<Hoover> hoover,
                           final Provider<RoomCleaningRepository> repo,
                           final Environment environment) {
        this.hooverProvider = hoover;
        this.repositoryProvider = repo;
        this.environment = environment;
    }

    public ResultPage clean(final RoomInput input) {
        final Hoover hoover = this.hooverProvider.get();
        final boolean requiresCleaning = hoover.initializeRoom(input.getRoom());
        final ResultPage result;
        if(requiresCleaning) {
            hoover.place(input.getBotCoords());
            hoover.clean(input.getInstructions());
            result = new ResultPage(hoover.getPosition(), hoover.getCleanedTiles());
        } else {
            result = new ResultPage(input.getBotCoords(), 0);
        }
        // if persistence profile is active, persist input and output with a timestamp
        if (Arrays.asList(environment.getActiveProfiles()).contains(PersistenceConfig.PERSISTENCE_PROFILE)) {
            repositoryProvider.get().save(new RoomCleaning(input, result, new Date().getTime()));
        }
        return result;
    }
}
