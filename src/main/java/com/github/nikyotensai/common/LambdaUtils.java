package com.github.nikyotensai.common;

import org.apache.commons.lang3.StringUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LambdaUtils {

    private static final Map<Class, WeakReference<Method>> CLASS_CACHE = new ConcurrentHashMap<>();
    private static final Map<PropertyFunc, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

    public static <T> String getFieldName(PropertyFunc<T, ?> func) {
        SerializedLambda serializedLambda = getSerializedLambda(func);
        return resolveFieldName(serializedLambda.getImplMethodName());
    }

    public static <T> SerializedLambda getSerializedLambda(PropertyFunc<T, ?> func) {
        return Optional.ofNullable(FUNC_CACHE.get(func))
                .map(WeakReference::get)
                .orElseGet(() -> {
                    try {
                        Method method = getLambdaMethod(func.getClass());
                        method.setAccessible(Boolean.TRUE);
                        SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
                        FUNC_CACHE.put(func, new WeakReference<>(serializedLambda));
                        return (SerializedLambda) method.invoke(func);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static Method getLambdaMethod(Class<?> clazz) {
        return Optional.ofNullable(CLASS_CACHE.get(clazz))
                .map(WeakReference::get)
                .orElseGet(() -> {
                    Method method = null;
                    try {
                        method = clazz.getDeclaredMethod("writeReplace");
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    method.setAccessible(Boolean.TRUE);
                    CLASS_CACHE.put(clazz, new WeakReference<>(method));
                    return method;
                });
    }


    private static String resolveFieldName(String getMethodName) {
        if (getMethodName.startsWith("get")) {
            getMethodName = getMethodName.substring(3);
        } else if (getMethodName.startsWith("is")) {
            getMethodName = getMethodName.substring(2);
        }
        return firstToLowerCase(getMethodName);
    }

    private static String firstToLowerCase(String param) {
        if (StringUtils.isBlank(param)) {
            return param;
        }
        return param.substring(0, 1).toLowerCase() + param.substring(1);
    }
}

