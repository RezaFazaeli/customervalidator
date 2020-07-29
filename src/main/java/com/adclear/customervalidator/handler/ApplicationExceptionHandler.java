package com.adclear.customervalidator.handler;

import com.adclear.customervalidator.exception.ApplicationMessage;
import com.adclear.customervalidator.exception.ApplicationMessageTypeEnum;
import com.adclear.customervalidator.exception.CustomerNotFoundException;
import com.adclear.customervalidator.exception.InvalidCustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;


/**
 * @author R.Fazaeli
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<Object> handleInvalidCustomerException(InvalidCustomerException exception) {
        log.error("There is an invalid customer !");
        ApplicationMessage
                message = ApplicationMessage.builder().type(ApplicationMessageTypeEnum.ERROR.getType()).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        log.error("Customer not found !");
        ApplicationMessage
                message = ApplicationMessage.builder().type(ApplicationMessageTypeEnum.ERROR.getType()).message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMalformedJsonException(HttpMessageNotReadableException exception) {
        log.error("There is a malformed JSON !");
        ApplicationMessage message = ApplicationMessage.builder().type(ApplicationMessageTypeEnum.ERROR.getType()).message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException(Exception exception) {
        log.error("There is a general exception : {}", exception.getMessage());
        ApplicationMessage message = ApplicationMessage.builder().type(ApplicationMessageTypeEnum.ERROR.getType()).message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }


}