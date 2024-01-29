package com.ErZet.blog.exception;

import com.ErZet.blog.dto.DBSResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)    // spring ex handler
    public ResponseEntity<DBSResponseEntity> handlingValidationExceptions
            (MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();
        dbsResponseEntity.setMessage(errorMessage);

        return new ResponseEntity<>(dbsResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)     // my own exception handler
    public ResponseEntity<DBSResponseEntity> handleRecordNotFoundExceptions
            (RecordNotFoundException ex) {
        DBSResponseEntity dbsResponseEntity = new DBSResponseEntity();
        dbsResponseEntity.setMessage(ex.message);
        return new ResponseEntity<>(dbsResponseEntity, HttpStatus.NOT_FOUND);
    }
}
