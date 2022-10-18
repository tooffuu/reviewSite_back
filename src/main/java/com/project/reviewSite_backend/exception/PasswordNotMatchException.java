package com.project.reviewSite_backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PasswordNotMatchException extends RuntimeException{

    public PasswordNotMatchException(String message){
        super(message);
    }
}
