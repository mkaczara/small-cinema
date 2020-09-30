package pl.mkaczara.smallcinema.schedule.rest.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Component
class ScheduleDTOMapper {

    fun map(scheduleDTO: ScheduleDTO): Schedule =
            Schedule(
                    scheduleDTO.id,
                    scheduleDTO.movieId,
                    scheduleDTO.dayOfWeek,
                    LocalTime.parse(scheduleDTO.time, DateTimeFormatter.ofPattern("HH:mm")),
                    scheduleDTO.normalPrice,
                    scheduleDTO.discountPrice
            )

    fun map(schedule: Schedule): ScheduleDTO =
            ScheduleDTO(
                    schedule.id,
                    schedule.movieId,
                    schedule.dayOfWeek,
                    schedule.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    schedule.normalPrice,
                    schedule.discountPrice
            )
}