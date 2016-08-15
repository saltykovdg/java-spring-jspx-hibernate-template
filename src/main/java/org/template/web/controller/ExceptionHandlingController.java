package org.template.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.template.util.Constants;

@ControllerAdvice
public class ExceptionHandlingController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404Error(NoHandlerFoundException e) {
        return Constants.Url.REDIRECT + Constants.Url.ROOT;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleAllErrors(Throwable e) {
        logger.error(e.toString(), e);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(Constants.View.ERROR_ALL);
        return mav;
    }
}