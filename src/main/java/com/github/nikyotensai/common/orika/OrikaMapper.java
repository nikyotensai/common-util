package com.github.nikyotensai.common.orika;

import com.github.nikyotensai.common.spi.ServiceLoader;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;


@SuppressWarnings("unused")
public class OrikaMapper {

    private static MapperFacade mapper;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        // orika extension
        OrikaRegistry orikaRegistry = ServiceLoader.loadSevice(OrikaRegistry.class);
        if (orikaRegistry != null) {
            orikaRegistry.registerConverters(mapperFactory);
            orikaRegistry.registerClassMaps(mapperFactory);
        }
        mapper = mapperFactory.getMapperFacade();
    }

    public static <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapper.map(sourceObject, destinationClass);
    }

    public static <S, D> void map(final S sourceObject, final D destinationObject) {
        mapper.map(sourceObject, destinationObject);
    }

    public static <S, D> void map(final S sourceObject, final D destinationObject, Type<S> sourceType, Type<D> destinationType) {
        mapper.map(sourceObject, destinationObject, sourceType, destinationType);
    }

    /**
     * If you need higher performance, {@link OrikaMapper#mapAsList(Iterable, Class, Class)} is recommended,
     * as {@link List} has a generic type,getting its type costs much.
     */
    public static <S, D> List<D> mapAsList(Iterable<S> sourceList, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, destinationClass);
    }

    public static <S, D> List<D> mapAsList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    public static <S, D> List<D> mapAsList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsList(sourceList, sourceType, destinationType);
    }

    public static <S, D> List<D> mapAsList(S[] source, Class<D> destinationClass) {
        return mapper.mapAsList(source, destinationClass);
    }

    public static <S, D> List<D> mapAsList(S[] source, Class<S> sourceClass, Class<D> destinationClass) {
        return mapper.mapAsList(source, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    public static <S, D> D[] mapAsArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return mapper.mapAsArray(destination, source, destinationClass);
    }

    public static <S, D> D[] mapAsArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsArray(destination, source, sourceType, destinationType);
    }

    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }
}