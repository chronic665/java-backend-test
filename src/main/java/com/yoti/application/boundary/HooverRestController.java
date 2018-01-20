package com.yoti.application.boundary;

import com.yoti.application.control.CleaningService;
import com.yoti.application.dto.ResultPage;
import com.yoti.application.dto.RoomInputDTO;
import com.yoti.application.entity.RoomInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HooverRestController {

    private final ConversionService conversionService;
    private final CleaningService cleaningService;

    @Autowired
    public HooverRestController(ConversionService conversionService, final CleaningService cleaningService) {
        this.conversionService = conversionService;
        this.cleaningService = cleaningService;
    }

    @PostMapping
    public ResultPage clean(@RequestBody final RoomInputDTO input) {
        return cleaningService.clean(conversionService.convert(input, RoomInput.class));
    }
}
