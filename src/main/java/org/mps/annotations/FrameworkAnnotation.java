package org.mps.annotations;

import org.mps.enums.CategoryType;
import org.mps.enums.Priority;
import org.mps.enums.Severity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface FrameworkAnnotation {

    public String[] author();
    public CategoryType[] category();

    public Severity[] severity();

    public Priority[] priority();
}
