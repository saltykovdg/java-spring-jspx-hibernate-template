package org.template.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.template.util.Constants;

@Controller
@RequestMapping(Constants.Url.USER)
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String showUserPage() {
        return Constants.View.USER;
    }
}