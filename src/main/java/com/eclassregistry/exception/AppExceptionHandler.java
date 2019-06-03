/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Grupa1
 */
@ControllerAdvice
public class AppExceptionHandler {
    
    @ExceptionHandler(value=Exception.class)
    public String handleError(){
        return "error";
    }
    
}
