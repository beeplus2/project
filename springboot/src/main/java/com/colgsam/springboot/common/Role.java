package com.colgsam.springboot.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色注解
 * 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Documented
public @interface Role{
    // 默认
    String value() default "user";

    // 拥有其中一个角色就通过
    String[] roles() default {};

    // 必须这些角色才通过
    String[] rolesMust() default {};
}
