package com.samin.dosan.core.formatter.address;

import com.samin.dosan.core.code.AddressType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface AddressFormat {
    AddressType type() default AddressType.FULL;
}
