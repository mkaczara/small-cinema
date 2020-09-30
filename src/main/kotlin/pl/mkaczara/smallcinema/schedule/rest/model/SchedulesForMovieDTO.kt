package pl.mkaczara.smallcinema.schedule.rest.model

import io.swagger.annotations.ApiModelProperty

class SchedulesForMovieDTO(
        @ApiModelProperty("The movie id", required = true) val movieId: Long,
        @ApiModelProperty("The movie schedules grouped by day of week (1-7)", required = true) val schedulesByDayOfWeek: Map<Int, Collection<ScheduleDTO>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SchedulesForMovieDTO

        if (schedulesByDayOfWeek != other.schedulesByDayOfWeek) return false

        return true
    }

    override fun hashCode(): Int {
        return schedulesByDayOfWeek.hashCode()
    }

    override fun toString(): String {
        return "SchedulesForMovieDTO(schedulesByDayOfWeek=$schedulesByDayOfWeek)"
    }

}