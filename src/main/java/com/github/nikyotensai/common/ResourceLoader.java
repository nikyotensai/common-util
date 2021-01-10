package com.github.nikyotensai.common;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@SuppressWarnings("unused")
public class ResourceLoader {

    public static Map<String, ResourceLoader> cache = new ConcurrentHashMap<>();

    private Enumeration<URL> configs = null;

    public static ResourceLoader getInstance(String fullName) {
        return cache.computeIfAbsent(fullName, name -> new ResourceLoader(name));
    }

    private ResourceLoader(String fullName) {
        try {
            configs = ResourceLoader.class.getClassLoader().getResources(fullName);
        } catch (IOException ex) {
            log.error("load resource[{}] failed.", fullName, ex);
        }
    }


    public URL next() {
        if (!configs.hasMoreElements()) {
            return null;
        }
        return configs.nextElement();
    }

    public List<URL> getAllUrls() {
        List<URL> urls = new ArrayList<>();
        while (configs.hasMoreElements()) {
            urls.add(configs.nextElement());
        }
        urls.sort((url1, url2) -> url2.toString().compareTo(url1.toString()));
        return urls;
    }

}