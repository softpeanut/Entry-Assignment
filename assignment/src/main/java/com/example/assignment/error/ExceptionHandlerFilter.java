package com.example.assignment.error;

import com.example.assignment.error.exception.BusinessException;
import com.example.assignment.error.exception.ExceptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode().getMessage());

            response.setStatus(e.getErrorCode().getStatus());
            response.setContentType("application/json");
            response.getWriter().write(convertToJson(exceptionResponse));
        }
    }

    public String convertToJson(Object object) throws JsonProcessingException {
        if(object == null)
            return null;

        return new ObjectMapper().writeValueAsString(object);
    }

}
