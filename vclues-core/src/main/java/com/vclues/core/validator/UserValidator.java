package com.vclues.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vclues.core.entity.User;
import com.vclues.core.service.IUserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "errors.minlength", new Object[] {"Email", "6"}, "Email cannot be less than 6 characters long.");
        }
        
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "errors.existing.user", new Object[] {user.getEmail(), user.getEmail()}, "Email already used");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "errors.minlength", new Object[] {"Passowrd", "8"}, "Password cannot be less than 8 characters long.");
        }
        /*
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        */
    }
}