package pl.mkaczara.smallcinema.schedule.rest.service

import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO

interface RestScheduleService {

    fun save(scheduleDTO: ScheduleDTO): ScheduleDTO

    fun update(scheduleDTO: ScheduleDTO): ScheduleDTO

    fun delete(scheduleId: Long): ScheduleDTO

    fun getForMovie(movieId: Long): SchedulesForMovieDTO
}