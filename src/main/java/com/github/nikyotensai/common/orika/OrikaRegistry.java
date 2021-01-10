package com.github.nikyotensai.common.orika;

import com.github.nikyotensai.common.spi.Spi;
import ma.glasnost.orika.MapperFactory;

@Spi(DefaultOrikaRegistry.class)
public interface OrikaRegistry {

    void registerConverters(MapperFactory mapperFactory);

    void registerClassMaps(MapperFactory mapperFactory);

}
