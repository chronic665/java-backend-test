package com.yoti.application.boundary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HooverRestControllerIT {

    private final String EXAMPLE_PAYLOAD = new StringBuilder("{")
            .append("\"roomSize\" : [5, 5],")
            .append("\"coords\" : [1, 2],")
            .append("\"patches\" : [")
            .append("  [1, 0],")
            .append("  [2, 2],")
            .append("  [2, 3]")
            .append("],")
            .append("\"instructions\" : \"NNESEESWNWW\"")
            .append("}")
            .toString();

    private final String REACH_BOUNDARY_PAYLOAD = new StringBuilder("{")
            .append("\"roomSize\" : [5, 5],")
            .append("\"coords\": [2, 1],")
            .append("\"patches\" : [")
            .append("[3, 0],")
            .append("[4, 1]")
            .append("],")
            .append("\"instructions\" : \"SSESNEESNN\"")
            .append("}")
            .toString();

    private final String NO_INSTRUCTIONS_INPUT = new StringBuilder("{")
            .append("\"roomSize\" : [5, 5],")
            .append("\"coords\": [2, 1],")
            .append("\"patches\" : [")
            .append("[3, 0],")
            .append("[4, 1]")
            .append("]")
            .append("}")
            .toString();

    private final String ILLEGAL_ROOM_PAYLOAD_MINUS= new StringBuilder("{")
            .append("\"roomSize\" : [-1, -1],")
            .append("\"coords\": [2, 1],")
            .append("\"patches\" : [")
            .append("[3, 0],")
            .append("[4, 1]")
            .append("],")
            .append("\"instructions\" : \"SSESNEESNN\"")
            .append("}")
            .toString();

    private final String ILLEGAL_ROOM_PAYLOAD_MISSING = new StringBuilder("{")
            .append("\"coords\": [2, 1],")
            .append("\"patches\" : [")
            .append("[3, 0],")
            .append("[4, 1]")
            .append("],")
            .append("\"instructions\" : \"SSESNEESNN\"")
            .append("}")
            .toString();

    private final String NO_PATCHES_INPUT = new StringBuilder("{")
            .append("\"roomSize\" : [5, 5],")
            .append("\"coords\": [2, 1],")
            .append("\"instructions\" : \"SSESNEESNN\"")
            .append("}")
            .toString();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExample() throws Exception {
        mockMvc.perform(post("/")
                    .content(EXAMPLE_PAYLOAD)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"coords\":[1,3],\"patches\":1}"))
        ;
    }

    @Test
    public void testBouncingOfWalls() throws Exception {
        mockMvc.perform(post("/")
                    .content(REACH_BOUNDARY_PAYLOAD)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"coords\":[4,2],\"patches\":2}"))
        ;
    }

    @Test
    public void testIllegalRoomMissing() throws Exception {
        mockMvc.perform(post("/")
                    .content(ILLEGAL_ROOM_PAYLOAD_MISSING)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void testIllegalRoomNegativeCoords() throws Exception {
        mockMvc.perform(post("/")
                    .content(ILLEGAL_ROOM_PAYLOAD_MINUS)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void testNoPatchesInput() throws Exception {
        mockMvc.perform(post("/")
                .content(NO_PATCHES_INPUT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"coords\":[2,1],\"patches\":0}"))
        ;
    }


    @Test
    public void testNoInstructions() throws Exception {
        mockMvc.perform(post("/")
                .content(NO_INSTRUCTIONS_INPUT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"coords\":[2,1],\"patches\":0}"))
        ;
    }

}