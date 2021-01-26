package com.github.nikyotensai.common.orika;

import javafx.util.Pair;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class DefaultOrikaRegistry implements OrikaRegistry {

    @Override
    public void registerConverters(MapperFactory mapperFactory) {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        for (Converter<?, ?> converter : getConverters()) {
            converterFactory.registerConverter(converter);
        }
    }

    @Override
    public void registerClassMaps(MapperFactory mapperFactory) {
        for (Map.Entry<Pair<Class, Class>, List<Pair<String, String>>> entry : ClassMapConfig.getDefaultConfig().getClassFieldMap().entrySet()) {
            Pair<Class, Class> classPair = entry.getKey();
            ClassMapBuilder mapBuilder = mapperFactory.classMap(classPair.getKey(), classPair.getValue())
                    .byDefault();
            for (Pair<String, String> fieldPair : entry.getValue()) {
                mapBuilder.field(fieldPair.getKey(), fieldPair.getValue());
            }
            mapBuilder.register();
        }
    }

    public List<Converter> getConverters() {
        return Collections.emptyList();
    }

}