package ru.iliya.controllers;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {
    Logger log = LogManager.getLogger(ErrorController.class);

    @ExceptionHandler(Exception.class)
    @GetMapping("/error")
    public String oneException(Exception e, WebRequest web, Model model) {
        model.addAttribute("error", e.getMessage());
        log.error(e.getMessage());
        return "error-page";
    }

}
