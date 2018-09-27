package net.luvina.dev.validate;

import net.luvina.dev.model.Admin;
import net.luvina.dev.util.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



@Component
public class ValidateLogin implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Admin admin = (Admin) object;
        String username = admin.getUsername();
        String password = admin.getPassword();
        if(username.isEmpty()){
            errors.rejectValue("username", Constant.ERROR_USERNAME);
        }
        if(password.isEmpty()){
            errors.rejectValue("password",Constant.ERROR_PASSWORD);
        }
    }
}
