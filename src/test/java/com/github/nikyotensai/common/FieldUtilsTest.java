package com.github.nikyotensai.common;

import com.github.nikyotensai.common.orika.A;
import org.junit.Test;

public class FieldUtilsTest {

    @Test
    public void test() {
        System.out.println(LambdaUtils.getFieldName(A::getId));
    }

}

