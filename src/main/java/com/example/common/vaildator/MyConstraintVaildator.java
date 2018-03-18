package com.example.common.vaildator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解实现
 */
public class MyConstraintVaildator implements ConstraintValidator<MyConstraint,String>{

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //校验逻辑
        return false;
    }
}
