package pl.mkaczara.smallcinema.schedule.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO
import pl.mkaczara.smallcinema.schedule.rest.service.RestScheduleService

@Api(description = "Operations related to movie schedule")
@RestController
@RequestMapping("/api/v1")
class ScheduleController(
        val scheduleService: RestScheduleService
) {

    @ApiOperation("Get all schedules for movie with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved movie schedules")
    ])
    @GetMapping("/schedule/{movieId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSchedules(@PathVariable("movieId") movieId: Long): SchedulesForMovieDTO =
            scheduleService.getForMovie(movieId)
}