package br.com.justice.application.patch;

import br.com.justice.application.util.Mappable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Patch {
  Class service();

  Class id();

  String findMethod() default "findById";

  String mapMethod() default "map";

  Class mapper() default Mappable.class;
}