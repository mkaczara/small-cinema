package pl.mkaczara.smallcinema.schedule.rest.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.mkaczara.smallcinema.schedule.logic.service.ScheduleService
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.rest.mapper.ScheduleDTOMapper
import pl.mkaczara.smallcinema.schedule.rest.mapper.SchedulesForMovieDTOMapper
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO
import java.math.BigDecimal
import java.time.LocalTime

@ExtendWith(MockKExtension::class)
class RestScheduleServiceImplTest {

    @MockK
    lateinit var scheduleService: ScheduleService

    @MockK
    lateinit var scheduleDTOMapper: ScheduleDTOMapper

    @MockK
    lateinit var schedulesForMovieDTOMapper: SchedulesForMovieDTOMapper

    @InjectMockKs
    lateinit var restScheduleService: RestScheduleServiceImpl

    @Test
    fun `should save`() {
        val inputScheduleDTO = ScheduleDTO(5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val inputSchedule = Schedule(5L, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val outputSchedule = Schedule(1L, 5L, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val expectation = ScheduleDTO(1L, 5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        every { scheduleDTOMapper.map(inputScheduleDTO) } returns inputSchedule
        every { scheduleService.saveOrUpdate(inputSchedule) } returns outputSchedule
        every { scheduleDTOMapper.map(outputSchedule) } returns expectation

        val result = restScheduleService.save(inputScheduleDTO)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when schedule to save has non-null id`() {
        val inputScheduleDTO = ScheduleDTO(1L, 5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))

        assertThrows(IllegalArgumentException::class.java) {
            restScheduleService.save(inputScheduleDTO)
        }
    }

    @Test
    fun `should update`() {
        val scheduleDTO = ScheduleDTO(1L, 5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val schedule = Schedule(1L, 5L, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val expectation = scheduleDTO
        every { scheduleDTOMapper.map(scheduleDTO) } returns schedule
        every { scheduleService.saveOrUpdate(schedule) } returns schedule
        every { scheduleDTOMapper.map(schedule) } returns expectation

        val result = restScheduleService.update(scheduleDTO)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when schedule to delete has null id`() {
        val inputScheduleDTO = ScheduleDTO(5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))

        assertThrows(IllegalArgumentException::class.java) {
            restScheduleService.update(inputScheduleDTO)
        }
    }

    @Test
    fun `should delete`() {
        val scheduleId = 52L
        val schedule = Schedule(scheduleId, 5L, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        val expectation = ScheduleDTO(scheduleId, 5L, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13))
        every { scheduleService.delete(scheduleId) } returns schedule
        every { scheduleDTOMapper.map(schedule) } returns expectation

        val result = restScheduleService.delete(scheduleId)

        assertEquals(expectation, result)
    }

    @Test
    fun `should get schedules for movie`() {
        val movieId = 212L
        val schedules = listOf(Schedule(12L, movieId, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(23), BigDecimal.valueOf(13)))
        val expectation = SchedulesForMovieDTO(movieId, mapOf(1 to listOf(ScheduleDTO(12L, movieId, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13)))))
        every { scheduleService.getForMovie(movieId) } returns schedules
        every { schedulesForMovieDTOMapper.map(movieId, schedules) } returns expectation

        val result = restScheduleService.getForMovie(movieId)

        assertEquals(expectation, result)
    }
}