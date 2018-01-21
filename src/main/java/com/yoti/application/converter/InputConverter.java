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

    @PostConstruct
    public void postConstruct() {
        // TODO register with ConversionService
    }

    @Override
    public RoomInput convert(RoomInputDTO source) {

        RoomInput.RoomInputBuilder builder = new RoomInput.RoomInputBuilder();

        builder.withBotAt(convertCoords(source.getCoords()))
                .withInstructions(convertInstructions(source.getInstructions()))
                .withRoom(convertRoom(source.getRoomSize(), source.getPatches()));

        return builder.build();
    }

    private Room convertRoom(int[] roomSize, List<Integer[]> patches) {
        Coords roomCoords = convertCoords(roomSize);
        Room room = new Room(roomCoords.getX(), roomCoords.getY(), convertPatches(patches));
        return room;
    }

    private Set<Patch> convertPatches(List<Integer[]> patches) {
        return patches.stream()
                .map(patch -> new Patch(convertCoords(patch)))
                .collect(Collectors.toSet());
    }


    private List<Instruction> convertInstructions(String instructions) {
        final List<Instruction> result = new ArrayList<>();
        for (char c : instructions.toCharArray()) {
            result.add(Instruction.forLiteral(String.valueOf(c)));
        }
        return result;
    }

    private Coords convertCoords(int[] coords) {
        if(null == coords || coords.length < 2 || coords[0] < 0 || coords[1] < 0) {
            throw new IllegalCoordinatesException("Given coords '" + print(coords) + "' are not valid!");
        }
        return new Coords(coords[0], coords[1]);
    }

    private String print(int[] coords) {
        if(null == coords) return "[null]";
        final StringBuilder sb = new StringBuilder("[");
        for (int index = 0; index < coords.length; index++) {
            sb.append(coords[index]);
            if(index < coords.length) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private Coords convertCoords(Integer[] coords) {
        return convertCoords(new int[]{coords[0], coords[1]});
    }

}
