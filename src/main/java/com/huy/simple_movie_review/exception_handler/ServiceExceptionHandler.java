package com.huy.simple_movie_review.exception_handler;

import com.huy.simple_movie_review.dto.response.ErrorMessage;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> dataNotFoundExceptionHandler(DataNotFoundException ex,
                                                               HandlerMethod method,
                                                               HttpServletRequest request){

        ex.printStackTrace();
        ErrorMessage message = new ErrorMessage();
        message.setStatus(404);
        message.setMessage("Not found");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(message);

    }

    @ExceptionHandler(value= {RuntimeException.class})
    public ResponseEntity<Object> runTimeExceptionHandler(DataNotFoundException ex,
                                                          HandlerMethod method,
                                                          HttpServletRequest request){
        ex.printStackTrace();
        ErrorMessage message = new ErrorMessage();
        message.setStatus(500);
        message.setMessage("Invalid request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);

    }
}
