package com.wrp.raywing.common.typehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wrp
 * @since 2025/8/20 7:21
 **/
@Component
public class StringListTypeHandler extends AbstractListTypeHandler<String> {

    public StringListTypeHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected TypeReference<List<String>> specificType() {
        return new TypeReference<>() {};
    }
}
