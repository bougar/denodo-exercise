package es.lareira.denodo.adapter.api.advice;

import es.lareira.denodo.application.domain.model.exceptions.NoPurchasesFoundException;
import es.lareira.denodo.generated.model.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error. Please contact the administrator.";

    @ExceptionHandler(NoPurchasesFoundException.class)
    public ResponseEntity<ErrorDTO> handleNoPurchasesFoundException(NoPurchasesFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.code(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.code(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorDTO.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleUnknownException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDTO.setMessage(INTERNAL_SERVER_ERROR_MESSAGE);
        log.error("Internal Server Error: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
