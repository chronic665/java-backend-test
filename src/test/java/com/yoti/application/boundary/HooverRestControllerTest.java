package com.yoti.application.boundary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HooverRestControllerTest {

    private final String MOCK_PAYLOAD = new StringBuilder("{")
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

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {

        mockMvc.perform(post("/")
                    .content(MOCK_PAYLOAD)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}