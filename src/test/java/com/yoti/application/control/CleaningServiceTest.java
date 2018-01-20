package com.yoti.application.control;

import com.example.mockito.MockitoExtension;
import com.yoti.application.dto.ResultPage;
import com.yoti.application.entity.Coords;
import com.yoti.application.entity.Patch;
import com.yoti.application.entity.Room;
import com.yoti.application.entity.RoomInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceTest {

    @Mock
    private Provider<Hoover> mockHooverProvider;

    @InjectMocks
    private CleaningService cut;
    private Hoover mockHoover;

    @BeforeEach
    public void setup() {
        mockHoover = mock(Hoover.class);
        given(mockHooverProvider.get()).willReturn(mockHoover);
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

        verify(mockHoover, times(0)).place(any());
        verify(mockHoover, times(0)).clean(any());
    }

    @Test
    public void testCleaningNecessary() {
        given(mockHoover.initializeRoom(any())).willReturn(true);
        Coords coords = new Coords(0, 0);
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

        verify(mockHoover, times(1)).place(any());
        verify(mockHoover, times(1)).clean(any());
    }




}