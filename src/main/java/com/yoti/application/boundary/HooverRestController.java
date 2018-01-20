package com.yoti.application.boundary;

import com.yoti.application.dto.ResultPage;
import com.yoti.application.dto.RoomInputDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HooverRestController {


    @PostMapping
    public ResultPage clean(@RequestBody final RoomInputDTO input) {
        System.out.println(input);
        return null;
    }
}
