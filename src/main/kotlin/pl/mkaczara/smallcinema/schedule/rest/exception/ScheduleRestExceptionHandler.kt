package pl.mkaczara.smallcinema.schedule.rest.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import pl.mkaczara.smallcinema.common.rest.model.ErrorResponse
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleNotFoundException
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleTimeConflictException
import java.lang.reflect.UndeclaredThrowableException
import java.time.LocalDateTime

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ScheduleRestExceptionHandler {

    @ExceptionHandler(ScheduleTimeConflictException::class)
    fun handleConflictException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(getMessage(ex), HttpStatus.CONFLICT.name, LocalDateTime.now())
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(ScheduleNotFoundException::class)
    fun handleNotFoundException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(getMessage(ex), HttpStatus.NOT_FOUND.name, LocalDateTime.now())
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    private fun getMessage(ex: Exception): String? {
        if (ex is UndeclaredThrowableException) {
            return ex.undeclaredThrowable.message
        }
        return ex.message
    }
}