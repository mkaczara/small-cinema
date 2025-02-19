package pl.mkaczara.smallcinema.schedule.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.Authorization
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
    @ApiOperation("Add new movie schedule", authorizations = [Authorization("basicAuth")])
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added movie schedule"),
        ApiResponse(code = 401, message = "Unauthorized"),
        ApiResponse(code = 409, message = "Another movie is scheduled for the same day of week and time")
    ])
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    )
    @PostMapping("/admin/schedule", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addSchedule(@RequestBody schedule: ScheduleDTO): ScheduleDTO =
            scheduleService.save(schedule)

    @ApiOperation("Update movie schedule", authorizations = [Authorization("basicAuth")])
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully updated movie schedule"),
        ApiResponse(code = 401, message = "Unauthorized"),
        ApiResponse(code = 409, message = "Another movie is scheduled for the same day of week and time")
    ])
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    )
    @PutMapping("/admin/schedule", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateSchedule(@RequestBody schedule: ScheduleDTO): ScheduleDTO =
            scheduleService.update(schedule)

    @ApiOperation("Delete movie schedule", authorizations = [Authorization("basicAuth")])
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully deleted movie schedule"),
        ApiResponse(code = 401, message = "Unauthorized"),
        ApiResponse(code = 404, message = "Movie schedule for given id doesn't exist")
    ])
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    )
    @DeleteMapping("/admin/schedule/{scheduleId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateSchedule(@PathVariable("scheduleId") scheduleId: Long): ScheduleDTO =
            scheduleService.delete(scheduleId)
}