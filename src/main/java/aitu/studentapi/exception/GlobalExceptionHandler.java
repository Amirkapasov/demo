package aitu.studentapi.exception;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public String handleNotFound(StudentNotFoundException ex) {
        return ex.getMessage();
    }
}
