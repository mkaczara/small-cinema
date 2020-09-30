package pl.mkaczara.smallcinema.movie.rest.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pl.mkaczara.smallcinema.common.rest.model.ErrorResponse
import pl.mkaczara.smallcinema.movie.logic.exception.MovieDetailsNotFoundException
import pl.mkaczara.smallcinema.movie.logic.exception.MovieNotFoundException
import java.time.LocalDateTime

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class MovieRestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(MovieNotFoundException::class, MovieDetailsNotFoundException::class)
    fun handleNotFoundExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.message, HttpStatus.NOT_FOUND.name, LocalDateTime.now())
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}