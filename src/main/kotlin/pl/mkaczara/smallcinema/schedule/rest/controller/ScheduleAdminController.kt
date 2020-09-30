package pl.mkaczara.smallcinema.schedule.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import pl.mkaczara.smallcinema.schedule.rest.service.RestScheduleService

@Api(description = "Operations related to movie schedule administration")
@RestController
@RequestMapping("/api/v1")
class ScheduleAdminController(
        val scheduleService: RestScheduleService
) {
    @ApiOperation("Add new movie schedule")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added movie schedule"),
        ApiResponse(code = 409, message = "Another movie is scheduled for the same day of week and time")
    ])
    @PostMapping("/admin/schedule", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addSchedule(@RequestBody schedule: ScheduleDTO): ScheduleDTO =
            scheduleService.save(schedule)

    @ApiOperation("Update movie schedule")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully updated movie schedule"),
        ApiResponse(code = 409, message = "Another movie is scheduled for the same day of week and time")
    ])
    @PutMapping("/admin/schedule", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateSchedule(@RequestBody schedule: ScheduleDTO): ScheduleDTO =
            scheduleService.update(schedule)

    @ApiOperation("Delete movie schedule")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted movie schedule"),
        ApiResponse(code = 404, message = "Movie schedule for given id doesn't exist")
    ])
    @DeleteMapping("/admin/schedule/{scheduleId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateSchedule(@PathVariable("scheduleId") scheduleId: Long): ScheduleDTO =
            scheduleService.delete(scheduleId)
}