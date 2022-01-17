package com.alierqul.todolist.entity.configure;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqeUsernameValidator.class})
public @interface UniqeUsername {
  String message() default "{hoaxify.constraint.email.UniqueUsername.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}