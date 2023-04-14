package com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice //Contem config global p/ toda API
public class ApplicationControllerAdvice {

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        return  new ApiErrors(bindingResult);
    }

    //Retorna os erros da API
    @ExceptionHandler( BusinessException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessException ex){
        return  new ApiErrors(ex);
    }

    //Retorna o objeto e seu status
    @ExceptionHandler( ResponseStatusException.class )
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex){
        return  new ResponseEntity( new ApiErrors(ex), ex.getStatus());
    }

}
