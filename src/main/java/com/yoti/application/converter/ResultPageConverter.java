package com.yoti.application.converter;

import com.yoti.application.dto.ResultPage;
import com.yoti.application.dto.ResultPageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResultPageConverter implements Converter<ResultPage, ResultPageDTO> {
    @Override
    public ResultPageDTO convert(final ResultPage source) {
        return new ResultPageDTO(
                new int[] {source.getPosition().getX(), source.getPosition().getY()},
                source.getCleanedTiles()
        );
    }
}
