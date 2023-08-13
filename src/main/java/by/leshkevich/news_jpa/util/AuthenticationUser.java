package by.leshkevich.news_jpa.util;

import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUser {
    public static User getAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o = authentication.getPrincipal();
        if (o.equals("anonymousUser")) {
            return null;
        }
        SecurityUser securityUser = (SecurityUser) o;

        return securityUser.getUser();
    }
}
