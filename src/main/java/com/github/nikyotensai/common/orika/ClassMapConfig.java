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

    Map<Pair, List<Pair>> classFieldMap = new ConcurrentHashMap<>();

    ClassMapConfig config(PropertyFunc aFunc, PropertyFunc bFunc) {
        SerializedLambda aSerializedLambda = LambdaUtils.getSerializedLambda(aFunc);
        SerializedLambda bSerializedLambda = LambdaUtils.getSerializedLambda(bFunc);
        Class aClass = aSerializedLambda.getCapturingClass();
        Class bClass = bSerializedLambda.getCapturingClass();
        Pair<Class, Class> classPair = new Pair<>(aClass, bClass);
        List<Pair> fieldList = classFieldMap.computeIfAbsent(classPair, (pair) -> {
            return new ArrayList<>();
        });
        fieldList.add(new Pair<>(LambdaUtils.getFieldName(aFunc), LambdaUtils.getFieldName(bFunc)));
        return this;
    }
}
