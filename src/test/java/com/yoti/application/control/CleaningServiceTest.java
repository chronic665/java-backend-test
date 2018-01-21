package com.yoti.application.control;

import com.yoti.application.dto.ResultPage;
import com.yoti.application.entity.*;
import com.yoti.application.repo.RoomCleaningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import javax.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CleaningServiceTest {

    private Provider<Hoover> mockHooverProvider;
    private Environment mockEnvironment;
    private Provider<RoomCleaningRepository> mockRepoProvider;

    private CleaningService cut;

    private Hoover mockHoover;
    private RoomCleaningRepository mockRepo;

    @BeforeEach
    public void setup() {
        mockHoover = mock(Hoover.class, "mockHoover");
        mockRepo = mock(RoomCleaningRepository.class, "mockRepo");
        mockRepoProvider = mock(Provider.class);
        mockHooverProvider = mock(Provider.class);
        mockEnvironment = mock(Environment.class);

        given(mockRepoProvider.get()).willReturn(mockRepo);
        given(mockHooverProvider.get()).willReturn(mockHoover);
        given(mockEnvironment.getActiveProfiles()).willReturn(new String[]{"default"});

        cut = new CleaningService(mockHooverProvider, mockRepoProvider, mockEnvironment);
    }

    @Test
    public void testNoCleaningNecessary() {
        given(mockHoover.initializeRoom(any())).willReturn(false);
        Coords coords = new Coords(0, 0);
        RoomInput room = new RoomInput.RoomInputBuilder()
                .withRoom(new Room(2,2, Collections.emptySet()))
                .withBotAt(coords)
                .withInstructions(Collections.emptyList())
                .build();
        ResultPage result = cut.clean(room);
        assertThat(result.getCleanedTiles(), is(0));
        assertThat(result.getPosition(), is(coords));

        verify(mockHoover, times(0)).getPosition();
        verify(mockHoover, times(0)).getCleanedTiles();
        verify(mockHoover, times(0)).place(any());
        verify(mockHoover, times(0)).clean(any());
    }

    @Test
    public void testCleaningNecessary() {
        Coords coords = new Coords(0, 0);
        given(mockHoover.initializeRoom(any())).willReturn(true);
        given(mockHoover.getPosition()).willReturn(coords);
        given(mockHoover.getCleanedTiles()).willReturn(0);

        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        RoomInput room = new RoomInput.RoomInputBuilder()
                .withRoom(new Room(2,2, patches))
                .withBotAt(coords)
                .withInstructions(Collections.emptyList())
                .build();
        ResultPage result = cut.clean(room);
        assertThat(result.getCleanedTiles(), is(0));
        assertThat(result.getPosition(), is(coords));

        verify(mockHoover, times(1)).getPosition();
        verify(mockHoover, times(1)).getCleanedTiles();
        verify(mockHoover, times(1)).place(any());
        verify(mockHoover, times(1)).clean(any());
    }

    @Test
    public void testCleaningNecessaryWithPerstence() {
        given(mockEnvironment.getActiveProfiles()).willReturn(new String[]{PERSISTENCE_PROFILE});
        Coords coords = new Coords(0, 0);
        given(mockHoover.initializeRoom(any())).willReturn(true);
        given(mockHoover.getPosition()).willReturn(coords);
        given(mockHoover.getCleanedTiles()).willReturn(0);

        Set<Patch> patches = new HashSet<>();
        patches.add(new Patch(new Coords(1,1)));
        RoomInput room = new RoomInput.RoomInputBuilder()
                .withRoom(new Room(2,2, patches))
                .withBotAt(coords)
                .withInstructions(Collections.emptyList())
                .build();
        ResultPage result = cut.clean(room);
        assertThat(result.getCleanedTiles(), is(0));
        assertThat(result.getPosition(), is(coords));

        verify(mockHoover, times(1)).getPosition();
        verify(mockHoover, times(1)).getCleanedTiles();
        verify(mockHoover, times(1)).place(any());
        verify(mockHoover, times(1)).clean(any());

        verify(mockRepo,times(1)).save(any(RoomCleaning.class));
    }

    @Test
    public void testNoCleaningNecessaryWithPersistence() {
        given(mockEnvironment.getActiveProfiles()).willReturn(new String[]{PERSISTENCE_PROFILE});
        given(mockHoover.initializeRoom(any())).willReturn(false);
        Coords coords = new Coords(0, 0);
        RoomInput room = new RoomInput.RoomInputBuilder()
                .withRoom(new Room(2,2, Collections.emptySet()))
                .withBotAt(coords)
                .withInstructions(Collections.emptyList())
                .build();
        ResultPage result = cut.clean(room);
        assertThat(result.getCleanedTiles(), is(0));
        assertThat(result.getPosition(), is(coords));

        verify(mockHoover, times(0)).getPosition();
        verify(mockHoover, times(0)).getCleanedTiles();
        verify(mockHoover, times(0)).place(any());
        verify(mockHoover, times(0)).clean(any());
        verify(mockRepo,times(1)).save(any(RoomCleaning.class));
    }


}