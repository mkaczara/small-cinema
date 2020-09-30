package pl.mkaczara.smallcinema.schedule.rest.service.impl

import org.springframework.stereotype.Service
import pl.mkaczara.smallcinema.schedule.logic.service.ScheduleService
import pl.mkaczara.smallcinema.schedule.rest.mapper.ScheduleDTOMapper
import pl.mkaczara.smallcinema.schedule.rest.mapper.SchedulesForMovieDTOMapper
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO
import pl.mkaczara.smallcinema.schedule.rest.service.RestScheduleService

@Service
class RestScheduleServiceImpl(
        val scheduleService: ScheduleService,
        val scheduleDTOMapper: ScheduleDTOMapper,
        val schedulesForMovieDTOMapper: SchedulesForMovieDTOMapper
) : RestScheduleService {

    override fun save(scheduleDTO: ScheduleDTO): ScheduleDTO {
        if (scheduleDTO.id != null) {
            throw IllegalArgumentException("Unable to save schedule with non-null id")
        }

        return saveOrUpdate(scheduleDTO)
    }

    override fun update(scheduleDTO: ScheduleDTO): ScheduleDTO {
        if (scheduleDTO.id == null) {
            throw IllegalArgumentException("Unable to update schedule with null id")
        }

        return saveOrUpdate(scheduleDTO)
    }

    override fun delete(scheduleId: Long): ScheduleDTO =
            scheduleDTOMapper.map(scheduleService.delete(scheduleId))


    override fun getForMovie(movieId: Long): SchedulesForMovieDTO =
            schedulesForMovieDTOMapper.map(movieId, scheduleService.getForMovie(movieId))

    private fun saveOrUpdate(schedule: ScheduleDTO): ScheduleDTO =
            scheduleDTOMapper.map(scheduleService.saveOrUpdate(scheduleDTOMapper.map(schedule)))
}