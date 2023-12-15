package es.lareira.denodo.adapter.api.advice;

import static org.junit.jupiter.api.Assertions.*;

import es.lareira.denodo.application.domain.model.exceptions.NoPurchasesFoundException;
import es.lareira.denodo.generated.model.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {
    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;
    @Test
    void when_handleNoPurchasesFoundException_then_not_found_status_code_is_returned() {
        ResponseEntity<ErrorDTO> response = customExceptionHandler.handleNoPurchasesFoundException(new NoPurchasesFoundException());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getCode());
    }

    @Test
    void when_handleConstraintViolationException_then_unprocessable_entity_status_code_is_returned() {
    ResponseEntity<ErrorDTO> response =
        customExceptionHandler.handleConstraintViolationException(new ConstraintViolationException("message", null));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(422, response.getBody().getCode());
    }

    @Test
    void when_handleUnknownException_then_internal_server_error_status_code_is_returned() {
        ResponseEntity<ErrorDTO> response = customExceptionHandler.handleUnknownException(new Exception());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(500, response.getBody().getCode());
    }
}
