package com.github.nikyotensai.common.orika;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMap;

import java.util.Collections;
import java.util.List;

public class DefaultOrikaRegistry implements OrikaRegistry {

    @Override
    public void registerConverters(MapperFactory mapperFactory) {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        for (Converter converter : getConverters()) {
            converterFactory.registerConverter(converter);
        }
    }

    @Override
    public void registerClassMaps(MapperFactory mapperFactory) {
        for (ClassMap classMap : getClassMaps()) {
            mapperFactory.registerClassMap(classMap);
        }
    }

    public List<Converter> getConverters() {
        return Collections.emptyList();
    }

    public List<ClassMap> getClassMaps() {
        return Collections.emptyList();
    }

}