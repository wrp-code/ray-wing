package com.wrp.raywing.blog.typehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wrp.raywing.blog.entity.TitleInfo;
import com.wrp.raywing.common.typehandler.AbstractListTypeHandler;

import java.util.List;

/**
 * @author wrp
 * @since 2025/8/20 7:21
 **/
public class TitleInfoListTypeHandler extends AbstractListTypeHandler<TitleInfo> {

    @Override
    protected TypeReference<List<TitleInfo>> specificType() {
        return new TypeReference<>() {};
    }
}
