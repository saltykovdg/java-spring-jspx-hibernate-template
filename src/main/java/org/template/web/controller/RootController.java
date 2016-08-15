package org.template.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.template.service.UserService;
import org.template.util.Constants;
import org.template.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = Constants.Url.ROOT, method = RequestMethod.GET)
    public String showRootPage() {
        return Constants.View.MAIN;
    }

    @RequestMapping(value = Constants.Url.ERROR_403, method = RequestMethod.GET)
    public String showError403Page() {
        return Constants.View.ERROR_403;
    }

    @RequestMapping(value = Constants.Url.LOGIN, method = RequestMethod.GET)
    public String showLoginPage(@RequestParam(value = Constants.RequestParam.ERROR, required = false) Boolean error,
                                @RequestParam(value = Constants.RequestParam.LOGOUT, required = false) Boolean logout,
                                Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                return Constants.Url.REDIRECT + Constants.Url.ROOT;
            }
        }
        if (error != null) {
            model.addAttribute(Constants.ModelAttribute.ERROR, Constants.Messages.PAGE_LOGIN_ERROR_INVALID_USERNAME_AND_PASSWORD);
        }
        if (logout != null) {
            model.addAttribute(Constants.ModelAttribute.LOGOUT, Constants.Messages.PAGE_LOGIN_MESSAGE_LOGOUT);
        }
        return Constants.View.LOGIN;
    }

    @RequestMapping(value = Constants.Url.SIGN_UP, method = RequestMethod.GET)
    public String showSignUpPage(Model model) {
        UserDto userDto = new UserDto();
        userDto.setAgree(false);
        model.addAttribute(UserDto.USER_DTO_ATTR, userDto);
        return Constants.View.SIGN_UP;
    }

    @RequestMapping(value = Constants.Url.SIGN_UP, method = RequestMethod.POST)
    public String signUp(@ModelAttribute(value = UserDto.USER_DTO_ATTR) @Valid UserDto userDto,
                         BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute(UserDto.USER_DTO_ATTR, userDto);
        } else {
            try {
                userService.signUpUser(userDto);
                return Constants.Url.REDIRECT + Constants.Url.LOGIN;
            } catch (Exception e) {
                model.addAttribute(Constants.ModelAttribute.ERROR, e.getMessage());
            }
        }
        return Constants.View.SIGN_UP;
    }
}
