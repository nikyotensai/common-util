package com.github.nikyotensai.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@SuppressWarnings("all")
public class SingletonUtils {

    private static final Map<Class, Object> instanceMap = new ConcurrentHashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        return (T) instanceMap.computeIfAbsent(clazz, (type) -> {
            try {
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                log.error("Create instance of type[{}] failed.Classes must have a zero-argument constructor that is not private.", clazz, ex);
                throw new CreationException(ex);
            }
        });
    }


}
