package com.yoti.application.control;

import com.yoti.application.dto.ResultPage;
import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Room;
import com.yoti.application.entity.RoomCleaning;
import com.yoti.application.entity.RoomInput;
import com.yoti.application.repo.RoomCleaningRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles(PERSISTENCE_PROFILE)
public class CleaningServicePersistenceIT {

    @Autowired
    private RoomCleaningRepository repo;

    @Autowired
    private CleaningService cut;

    @Test
    public void test() {
        Coords coords = new Coords(0, 0);
        RoomInput room = new RoomInput.RoomInputBuilder()
                .withRoom(new Room(2,2, Collections.emptySet()))
                .withBotAt(coords)
                .withInstructions(Collections.emptyList())
                .build();
        ResultPage result = cut.clean(room);

        List<RoomCleaning> list = repo.findAll();
        assertThat(list.size(), is(1));
    }
}