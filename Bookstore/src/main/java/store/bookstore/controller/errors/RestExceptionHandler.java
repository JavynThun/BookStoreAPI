package store.bookstore.controller.errors;

import org.hibernate.PropertyValueException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, PropertyValueException ex) {
        String error = "Error adding/updating book: " + ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, SQLIntegrityConstraintViolationException ex) {
        String error = "Error adding/updating book: " + ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, IllegalArgumentException ex) {
//        String error = ex.getMessage();
//        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
//    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handlePropertyValueException(HttpServletRequest req, NullPointerException ex) {
        String error = ex.getMessage();
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
    }
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }
}
