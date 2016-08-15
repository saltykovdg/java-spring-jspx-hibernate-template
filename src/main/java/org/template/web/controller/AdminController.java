package org.template.web.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.template.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.Url.ADMIN)
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPage() {
        return Constants.View.ADMIN;
    }
}