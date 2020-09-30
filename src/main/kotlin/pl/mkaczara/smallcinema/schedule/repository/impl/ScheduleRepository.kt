package pl.mkaczara.smallcinema.schedule.repository.impl

import org.springframework.data.repository.CrudRepository
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import java.time.LocalTime

interface ScheduleRepository : CrudRepository<Schedule, Long> {

    fun findByMovieId(movieId: Long): Collection<Schedule>

    fun findByDayOfWeekAndTime(dayOfWeek: Int, time: LocalTime): Schedule?
}