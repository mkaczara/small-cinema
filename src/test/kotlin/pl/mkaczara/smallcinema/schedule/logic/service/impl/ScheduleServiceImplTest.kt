package pl.mkaczara.smallcinema.schedule.logic.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleNotFoundException
import pl.mkaczara.smallcinema.schedule.logic.exception.ScheduleTimeConflictException
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.repository.impl.ScheduleRepository
import java.math.BigDecimal
import java.time.LocalTime
import java.util.Optional

@ExtendWith(MockKExtension::class)
internal class ScheduleServiceImplTest {

    @MockK
    lateinit var scheduleRepository: ScheduleRepository

    @InjectMockKs
    lateinit var scheduleService: ScheduleServiceImpl

    @Test
    fun `should save`() {
        val dayOfWeek = 5
        val time = LocalTime.now()
        val newSchedule = Schedule(5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(25))
        val expectation = Schedule(34L, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(25))
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns null
        every { scheduleRepository.save(newSchedule) } returns expectation

        val result = scheduleService.saveOrUpdate(newSchedule)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when save and schedule for the same time already exists`() {
        val dayOfWeek = 5
        val time = LocalTime.now()
        val newSchedule = Schedule(5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        val existingSchedule = Schedule(1L, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns existingSchedule

        assertThrows(ScheduleTimeConflictException::class.java) {
            scheduleService.saveOrUpdate(newSchedule)
        }
    }

    @Test
    fun `should update`() {
        val scheduleId = 34L
        val dayOfWeek = 5
        val time = LocalTime.now()
        val scheduleToUpdate = Schedule(scheduleId, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(25))
        val expectation = scheduleToUpdate
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns null
        every { scheduleRepository.save(scheduleToUpdate) } returns expectation

        val result = scheduleService.saveOrUpdate(scheduleToUpdate)

        assertEquals(expectation, result)
    }

    @Test
    fun `should update when schedule for the same time already exists but has the same id`() {
        val scheduleId = 34L
        val dayOfWeek = 5
        val time = LocalTime.now()
        val scheduleToUpdate = Schedule(scheduleId, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(25))
        val existingSchedule = Schedule(scheduleId, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        val expectation = scheduleToUpdate
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns existingSchedule
        every { scheduleRepository.save(scheduleToUpdate) } returns expectation

        val result = scheduleService.saveOrUpdate(scheduleToUpdate)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when update and schedule for the same time and different id already exists`() {
        val dayOfWeek = 5
        val time = LocalTime.now()
        val scheduleToUpdate = Schedule(4L, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        val existingSchedule = Schedule(5L, 5L, dayOfWeek, time, BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns existingSchedule

        assertThrows(ScheduleTimeConflictException::class.java) {
            scheduleService.saveOrUpdate(scheduleToUpdate)
        }
    }

    @Test
    fun `should delete`() {
        val scheduleId = 64L
        val scheduleToDelete = Schedule(scheduleId, 5L, 1, LocalTime.now(), BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        val expectation = scheduleToDelete
        every { scheduleRepository.findById(scheduleId) } returns Optional.of(scheduleToDelete)
        every { scheduleRepository.delete(scheduleToDelete) } returns Unit

        val result = scheduleService.delete(scheduleId)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when delete and schedule doesn't exist`() {
        val scheduleId = 64L
        every { scheduleRepository.findById(scheduleId) } returns Optional.empty()

        assertThrows(ScheduleNotFoundException::class.java) {
            scheduleService.delete(scheduleId)
        }
    }

    @Test
    fun `should get schedules for movie using repository`() {
        val movieId = 53L
        val schedule = Schedule(4L, movieId, 1, LocalTime.now(), BigDecimal.valueOf(43.4), BigDecimal.valueOf(24))
        val expectation = listOf(schedule)
        every { scheduleRepository.findByMovieId(movieId) } returns expectation

        val result = scheduleService.getForMovie(movieId)

        assertEquals(expectation, result)
    }
}