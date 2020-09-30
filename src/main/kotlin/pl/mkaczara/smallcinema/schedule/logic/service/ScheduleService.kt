package pl.mkaczara.smallcinema.schedule.logic.service

import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule

interface ScheduleService {

    fun saveOrUpdate(schedule: Schedule): Schedule

    fun delete(scheduleId: Long): Schedule

    fun getForMovie(movieId: Long): Collection<Schedule>
}