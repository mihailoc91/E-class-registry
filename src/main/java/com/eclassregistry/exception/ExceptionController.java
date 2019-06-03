/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.exception;

import com.eclassregistry.EClassRegistryApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Grupa1
 */
@ControllerAdvice
public class ExceptionController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EClassRegistryApplication.class);
    
    @ExceptionHandler(value = Exception.class)
    public String handleError(Exception ex){
        LOGGER.error(ex.getMessage());
  
        return "error";
    }
}
