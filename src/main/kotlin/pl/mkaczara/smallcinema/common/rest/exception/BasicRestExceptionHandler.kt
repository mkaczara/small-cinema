package pl.mkaczara.smallcinema.common.rest.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pl.mkaczara.smallcinema.common.rest.model.ErrorResponse
import java.time.LocalDateTime

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class BasicRestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.message, HttpStatus.INTERNAL_SERVER_ERROR.name, LocalDateTime.now())
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}