package com.fenrir.colorme.common.exception;

import com.fenrir.colorme.common.exception.message.ConstraintViolationErrorMessage;
import com.fenrir.colorme.common.exception.message.ConstraintViolationInfo;
import com.fenrir.colorme.common.exception.message.ErrorMessage;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import com.fenrir.colorme.palette.application.service.exception.TagsNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseExceptionHandler {
    private static final String CONSTRAINT_VIOLATION_MESSAGE = "Constraint Violation";
    private static final String HTTP_MESSAGE_NOT_READABLE_MESSAGE = "Failed to read request body";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    @ExceptionHandler({ PaletteNotFoundException.class, TagsNotFoundException.class })
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(Exception ex, WebRequest request) {
        List<ConstraintViolationInfo> constraintViolations = null;

        if (ex instanceof ConstraintViolationException cve) {
            constraintViolations = cve.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolationInfo::from)
                    .collect(Collectors.toList());
        } else if (ex instanceof MethodArgumentNotValidException manve) {
            constraintViolations = manve.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(ConstraintViolationInfo::from)
                    .toList();
        } else {
            handleUnknownException(ex, request);
        }

        ErrorMessage message = new ConstraintViolationErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                CONSTRAINT_VIOLATION_MESSAGE,
                request.getDescription(false),
                constraintViolations
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                HTTP_MESSAGE_NOT_READABLE_MESSAGE,
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMostSpecificCause().getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorMessage> handleUnknownException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                INTERNAL_SERVER_ERROR_MESSAGE,
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
