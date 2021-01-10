package com.github.nikyotensai.common.spi;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Spi {
    /**
     * the same sa {@link #defaultImpl}
     *
     * @return
     */
    Class value() default void.class;

    /**
     * default impl
     *
     * @return
     */
    Class defaultImpl() default void.class;
}
