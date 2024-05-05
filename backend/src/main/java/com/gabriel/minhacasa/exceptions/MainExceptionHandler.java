package com.gabriel.minhacasa.exceptions;

import com.gabriel.minhacasa.exceptions.customizeExceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ObjectNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getBindingResult().getFieldError().getField() + " " + ex.getFieldError().getDefaultMessage(),
                request.getRequestURI(),
                "MethodArgumentNotValidException");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "ConstraintViolationException");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "HttpMessageNotReadableException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMostSpecificCause().getMessage(),
                request.getRequestURI(),
                "DataIntegrityViolationException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> transactionSystemException(TransactionSystemException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getLocalizedMessage(),
                request.getRequestURI(),
                "TransactionSystemException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "UserNotFoundException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardError> ioException(IOException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "ioException");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<StandardError> missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "MissingServletRequestPartException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationErrorException.class)
    public ResponseEntity<StandardError> authenticationErrorException(AuthenticationErrorException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "AuthenticationErrorException");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataTypeNotAcceptedForThisOperationException.class)
    public ResponseEntity<StandardError> dataTypeNotAcceptedForThisOperationException(DataTypeNotAcceptedForThisOperationException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("DataTypeNotAcceptedForThisOperationException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ErrorForDeleteFileException.class)
    public ResponseEntity<StandardError> errorForDeleteFileException(ErrorForDeleteFileException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ErrorForDeleteFileException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorForDeleteImmobileException.class)
    public ResponseEntity<StandardError> errorForDeleteImmobileException(ErrorForDeleteImmobileException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ErrorForDeleteImmobileException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExtensionNotValidException.class)
    public ResponseEntity<StandardError> ExtensionNotValidException(ExtensionNotValidException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ExtensionNotValidException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavoriteAlreadyExistsException.class)
    public ResponseEntity<StandardError> FavoriteAlreadyExistsException(FavoriteAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("FavoriteAlreadyExistsException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNullContentException.class)
    public ResponseEntity<StandardError> FileNullContentException(FileNullContentException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("FileNullContentException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImmobileNotFoundException.class)
    public ResponseEntity<StandardError> ImmobileNotFoundException(ImmobileNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ImmobileNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImmobileNullContentException.class)
    public ResponseEntity<StandardError> ImmobileNullContentException(ImmobileNullContentException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("ImmobileNullContentException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IntegrityErrorException.class)
    public ResponseEntity<StandardError> IntegrityErrorException(IntegrityErrorException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("IntegrityErrorException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SaveFileErrorException.class)
    public ResponseEntity<StandardError> SaveFileErrorException(SaveFileErrorException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("SaveFileErrorException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TheUserAlreadyHasAnImageProfileException.class)
    public ResponseEntity<StandardError> TheUserAlreadyHasAnImageProfileException(TheUserAlreadyHasAnImageProfileException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("TheUserAlreadyHasAnImageProfileException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNullContentException.class)
    public ResponseEntity<StandardError> UserNullContentException(UserNullContentException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .fieldError("UserNullContentException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
