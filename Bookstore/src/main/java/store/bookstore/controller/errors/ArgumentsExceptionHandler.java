package store.bookstore.controller.errors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ArgumentsExceptionHandler extends IllegalArgumentException{
    @ExceptionHandler(NoAuthorException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, NoAuthorException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(NoBookFoundException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, NoBookFoundException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(EmptyStringException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, EmptyStringException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(NoPriceException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, NoPriceException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(UsernameInvalidException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, UsernameInvalidException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, InvalidCredentialsException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }
}
