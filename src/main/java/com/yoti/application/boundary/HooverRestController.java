package com.yoti.application.boundary;

import com.yoti.application.control.CleaningService;
import com.yoti.application.dto.ResultPage;
import com.yoti.application.dto.ResultPageDTO;
import com.yoti.application.dto.RoomInputDTO;
import com.yoti.application.entity.RoomInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class HooverRestController extends ValidatedRestController{

    private final ConversionService conversionService;
    private final CleaningService cleaningService;

    @Autowired
    public HooverRestController(ConversionService conversionService, final CleaningService cleaningService) {
        this.conversionService = conversionService;
        this.cleaningService = cleaningService;
    }

    @PostMapping
    public ResultPageDTO clean(@RequestBody final RoomInputDTO input) {
        return conversionService.convert(
                cleaningService.clean(conversionService.convert(input, RoomInput.class)),
                ResultPageDTO.class)
                ;
    }
}
