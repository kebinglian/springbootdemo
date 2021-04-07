package com.kbldemo.config.aspect;

import java.lang.annotation.*;
/**
 * <p>
 *  自定义注解类
 * </p>
 *
 * @author kbl
 * @since 2021-03-11
 */
@Target(ElementType.METHOD) //,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface SysAopLog {
    String value() default "";
}
