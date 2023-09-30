package com.souzatech.apiprodutos.exception.handler;

import com.souzatech.apiprodutos.dto.error.StandardError;
import com.souzatech.apiprodutos.exception.DataIntegrityViolationException;
import com.souzatech.apiprodutos.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException n, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder()
                        .timestamp(Instant.now())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .error(n.getMessage())
                        .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException d,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(StandardError.builder()
                .timestamp(Instant.now())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .error("Data Integrity Violation Exception")
                .message(d.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}
