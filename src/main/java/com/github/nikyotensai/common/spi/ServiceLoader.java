package com.github.nikyotensai.common.spi;

import com.github.nikyotensai.common.ResourceLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

@Slf4j
@SuppressWarnings("unchecked")
public class ServiceLoader {

    private static Properties spiProperties;

    public static <T> T loadSevice(Class<T> type) {
        Properties properties = getProperties();
        String implClsName = properties.getProperty(type.getName());
        try {
            return (T) Class.forName(implClsName).newInstance();
        } catch (Exception ex) {
            log.error("load Class failed,type[{}]", type.getName(), ex);
        }
        return null;
    }

    public static Properties getProperties() {
        if (spiProperties != null) {
            return spiProperties;
        }
        synchronized (ServiceLoader.class) {
            if (spiProperties != null) {
                return spiProperties;
            }
            Properties properties = new Properties();
            ResourceLoader rl = ResourceLoader.getInstance("META-INF/spi.properties");
            List<URL> urls = rl.getAllUrls();

            for (URL url : urls) {
                try (InputStream is = url.openStream()) {
                    properties.load(is);
                } catch (IOException ex) {
                    log.error("load properties failed.", ex);
                }
            }
            spiProperties = properties;
            return spiProperties;
        }
    }

}
