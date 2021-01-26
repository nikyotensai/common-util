package com.github.nikyotensai.common.orika;

import com.github.nikyotensai.common.LambdaUtils;
import com.github.nikyotensai.common.PropertyFunc;
import com.github.nikyotensai.common.SerializedLambda;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassMapConfig {


    public static final ClassMapConfig defaultConfig = new ClassMapConfig();

    Map<Pair<Class, Class>, List<Pair<String, String>>> classFieldMap = new ConcurrentHashMap<>();

    public <T1, T2> ClassMapConfig config(PropertyFunc<T1, ?> aFunc, PropertyFunc<T2, ?> bFunc) {
        SerializedLambda aSerializedLambda = LambdaUtils.getSerializedLambda(aFunc);
        SerializedLambda bSerializedLambda = LambdaUtils.getSerializedLambda(bFunc);
        Class aClass = aSerializedLambda.getRealImplClass();
        Class bClass = bSerializedLambda.getRealImplClass();
        Pair<Class, Class> classPair = new Pair<>(aClass, bClass);
        List<Pair<String, String>> fieldList = classFieldMap.computeIfAbsent(classPair, (pair) -> {
            return new ArrayList<>();
        });
        fieldList.add(new Pair<>(LambdaUtils.getFieldName(aFunc), LambdaUtils.getFieldName(bFunc)));
        return this;
    }

    public static ClassMapConfig getDefaultConfig() {
        return defaultConfig;
    }

    public Map<Pair<Class, Class>, List<Pair<String, String>>> getClassFieldMap() {
        return classFieldMap;
    }

}
