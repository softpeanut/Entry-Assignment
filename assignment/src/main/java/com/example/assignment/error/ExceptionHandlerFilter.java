package com.example.assignment.error;

import com.example.assignment.error.exception.BusinessException;
import com.example.assignment.error.exception.ExceptionResponse;
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
            ErrorCode errorCode = e.getErrorCode();
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(errorCode.getMessage());
            response.setStatus(errorCode.getStatus());
            response.setContentType("application/json");
            response.getWriter().write(exceptionResponse.toString());
        }
    }

}
