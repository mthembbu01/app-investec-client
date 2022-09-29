package com.tstcore.appinvestecclient.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class ClientExceptionController {
    // the date formatter for our date
    final DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    @ExceptionHandler(value = { ClientException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> exception(ClientException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                 ex.getMessage()
                ,HttpStatus.NOT_FOUND
                ,sdf.format(new Date())
                ,HttpStatus.NOT_FOUND.value()
                ,request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}

@Data
final class ErrorDetails {
    private final String request;
    private final String message;
    private final HttpStatus httpStatus;
    private final String timestamp;
    private final int status;

    public ErrorDetails(String message,
                        HttpStatus httpStatus,
                        String timestamp,
                        int status,
                        String request) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.status = status;
        this.request = request;
    }
}
