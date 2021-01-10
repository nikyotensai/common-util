package com.github.nikyotensai.common.orika;

import ma.glasnost.orika.MapperFactory;

public class DefaultOrikaRegistry implements OrikaRegistry {

    @Override
    public void registerConverters(MapperFactory mapperFactory) {
        // do nothing
    }

    @Override
    public void registerClassMaps(MapperFactory mapperFactory) {
        // do nothing
    }
}