package com.yoti.application;

import com.yoti.application.control.CleaningService;
import com.yoti.application.converter.InputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@SpringBootApplication
public class R2D2Application {

    @Autowired
    private InputConverter inputConverter;

    @Bean
    public ConversionService conversionService() {
        final DefaultConversionService cs = new DefaultConversionService();
        cs.addConverter(inputConverter);
        return cs;
    }
}
