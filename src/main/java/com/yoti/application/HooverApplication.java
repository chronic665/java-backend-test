package com.yoti.application;

import com.yoti.application.converter.InputConverter;
import com.yoti.application.converter.ResultPageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@SpringBootApplication
public class HooverApplication {

    @Autowired
    private InputConverter inputConverter;
    @Autowired
    private ResultPageConverter resultPageConverter;

    @Bean
    public ConversionService conversionService() {
        final DefaultConversionService cs = new DefaultConversionService();
        cs.addConverter(inputConverter);
        cs.addConverter(resultPageConverter);
        return cs;
    }
}
