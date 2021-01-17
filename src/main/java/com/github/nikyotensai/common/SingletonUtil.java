package com.github.nikyotensai.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@SuppressWarnings("all")
public class SingletonUtil {

    private static final Map<Class, Object> instanceMap = new ConcurrentHashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        return (T) instanceMap.computeIfAbsent(clazz, (type) -> {
            try {
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("create instance of type[{}] failed.", clazz, e);
            }
            return null;
        });
    }


}
