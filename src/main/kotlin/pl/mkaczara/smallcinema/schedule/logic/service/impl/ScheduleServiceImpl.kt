package pl.mkaczara.smallcinema.schedule.logic.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleNotFoundException
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleTimeConflictException
import pl.mkaczara.smallcinema.schedule.logic.service.ScheduleService
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.repository.impl.ScheduleRepository

@Service
class ScheduleServiceImpl(
        val scheduleRepository: ScheduleRepository
) : ScheduleService {

    @Transactional
    override fun saveOrUpdate(schedule: Schedule): Schedule {
        val existingSchedule = scheduleRepository.findByDayOfWeekAndTime(schedule.dayOfWeek, schedule.time)
        if (existingSchedule == null) {
            return scheduleRepository.save(schedule)
        } else {
            if (schedule.id != null && schedule.id == existingSchedule.id) {
                return scheduleRepository.save(schedule)
            }
            throw ScheduleTimeConflictException("Some movie is already scheduled for day of week: ${schedule.dayOfWeek}, time: ${schedule.time}")
        }
    }

    @Transactional
    override fun delete(scheduleId: Long): Schedule {
        val scheduleByIdOpt = scheduleRepository.findById(scheduleId)
        if (scheduleByIdOpt.isPresent) {
            val scheduleById = scheduleByIdOpt.get()
            scheduleRepository.delete(scheduleById)
            return scheduleById
        }
        throw ScheduleNotFoundException("Schedule for id $scheduleId not found")
    }

    override fun getForMovie(movieId: Long): Collection<Schedule> =
            scheduleRepository.findByMovieId(movieId)
}