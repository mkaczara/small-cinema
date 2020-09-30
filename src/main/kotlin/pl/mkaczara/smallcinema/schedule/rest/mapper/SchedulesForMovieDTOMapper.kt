package pl.mkaczara.smallcinema.schedule.rest.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO
import java.util.stream.Collectors

@Component
class SchedulesForMovieDTOMapper(
        val scheduleDTOMapper: ScheduleDTOMapper
) {

    fun map(movieId: Long, schedules: Collection<Schedule>): SchedulesForMovieDTO =
            SchedulesForMovieDTO(
                    movieId,
                    schedules.stream()
                            .map { schedule -> scheduleDTOMapper.map(schedule) }
                            .collect(Collectors.groupingBy { schedule -> schedule.dayOfWeek })
            )
}