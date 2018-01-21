package com.yoti.application.converter;

import com.yoti.application.dto.RoomInputDTO;
import com.yoti.application.entity.*;
import com.yoti.application.exception.IllegalCoordinatesException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InputConverter implements Converter<RoomInputDTO, RoomInput> {

    @Override
    public RoomInput convert(final RoomInputDTO source) {

        final RoomInput.RoomInputBuilder builder = new RoomInput.RoomInputBuilder();

        builder.withBotAt(convertCoords(source.getCoords()))
                .withInstructions(convertInstructions(source.getInstructions()))
                .withRoom(convertRoom(source.getRoomSize(), source.getPatches()));

        return builder.build();
    }

    private Room convertRoom(int[] roomSize, List<Integer[]> patches) {
        final Coords roomCoords = convertCoords(roomSize);
        return new Room(roomCoords.getX(), roomCoords.getY(), convertPatches(patches));
    }

    private Set<Patch> convertPatches(final List<Integer[]> patches) {
        if(patches == null || patches.isEmpty()) return Collections.emptySet();
        return patches.stream()
                .map(patch -> new Patch(convertCoords(patch)))
                .collect(Collectors.toSet());
    }


    private List<Instruction> convertInstructions(final String instructions) {
        if(null == instructions) return Collections.emptyList();
        final List<Instruction> result = new ArrayList<>();
        for (char c : instructions.toCharArray()) {
            result.add(Instruction.forLiteral(String.valueOf(c)));
        }
        return result;
    }

    private Coords convertCoords(final int[] coords) {
        if(null == coords || coords.length < 2 || coords[0] < 0 || coords[1] < 0) {
            throw new IllegalCoordinatesException("Given coords '" + print(coords) + "' are not valid!");
        }
        return new Coords(coords[0], coords[1]);
    }

    private String print(final int[] coords) {
        if(null == coords) return "[null]";
        final StringBuilder sb = new StringBuilder("[");
        for (int index = 0; index < coords.length; index++) {
            sb.append(coords[index]);
            if(index < coords.length) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private Coords convertCoords(final Integer[] coords) {
        return convertCoords(new int[]{coords[0], coords[1]});
    }

}
