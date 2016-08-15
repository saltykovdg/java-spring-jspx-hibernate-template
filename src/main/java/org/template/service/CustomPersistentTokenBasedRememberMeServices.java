package org.template.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomPersistentTokenBasedRememberMeServices extends PersistentTokenBasedRememberMeServices {
    private PersistentLoginService persistentLoginService;

    public CustomPersistentTokenBasedRememberMeServices(String key, UserDetailsService userDetailsService, PersistentLoginService persistentLoginService) {
        super(key, userDetailsService, persistentLoginService);
        this.persistentLoginService = persistentLoginService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        super.logout(request, response, authentication);

        if (authentication != null) {
            String rememberMeCookie = extractRememberMeCookie(request);
            if (rememberMeCookie != null && rememberMeCookie.length() != 0) {
                String[] cookieTokens = decodeCookie(rememberMeCookie);

                if (cookieTokens.length == 2) {
                    String series = cookieTokens[0];

                    //remove by series
                    persistentLoginService.removeUserTokenBySeries(series);
                }
            }
        }
    }
}