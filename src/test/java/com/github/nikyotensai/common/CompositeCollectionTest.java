package com.github.nikyotensai.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CompositeCollectionTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("1", "2");
        Set<String> set = Collections.singleton("aa");
        List<String> list2 = Collections.singletonList("AA");
        CompositeCollection<String> collection = new CompositeCollection<>();
        collection.combine(list)
                .combine(set)
                .combine(list2);
        for (String xx : collection) {
            System.out.println(xx);
        }
    }
}
