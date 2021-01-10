package com.github.nikyotensai.common.orika;

import org.junit.Test;

public class OrikaMapperTest {

    @Test
    public void test() {
        A a = new A();
        a.setId(1);
        a.setSubId(2);
        B b = OrikaMapper.map(a, B.class);
        System.out.println(b);
    }

}