package by.leshkevich.news_jpa.util;

import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> userFromBD = userService.getUserByEmail(user.getEmail());
        if (userFromBD.isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
