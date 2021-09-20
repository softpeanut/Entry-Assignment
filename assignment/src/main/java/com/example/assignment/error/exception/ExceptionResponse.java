package com.example.assignment.error.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;

    public String convertToJson(Object object) throws JsonProcessingException {
        if(object == null)
            return null;

        return new ObjectMapper().writeValueAsString(object);
    }
}
