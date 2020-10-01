package pl.mkaczara.smallcinema.schedule.repository.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import java.math.BigDecimal
import java.time.LocalTime

@DataJpaTest
class ScheduleRepositoryIT @Autowired constructor(
        val entityManager: TestEntityManager,
        val scheduleRepository: ScheduleRepository
) {

    @Test
    fun `should find by day of week and time`() {
        val dayOfWeek = 5
        val time = LocalTime.of(18, 0)
        entityManager.persist(Schedule(2L, dayOfWeek, LocalTime.of(10, 0), BigDecimal.valueOf(20.5), BigDecimal.valueOf(15.0)))
        val persistedExpectation = entityManager.persist(Schedule(2L, dayOfWeek, time, BigDecimal.valueOf(20.5), BigDecimal.valueOf(15.0)))

        val result = scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time)

        assertEquals(persistedExpectation, result)
    }

    @Test
    fun `should find by movie id`() {
        val movieId = 9L
        entityManager.persist(Schedule(3L, 1, LocalTime.of(15, 0), BigDecimal.valueOf(20.5), BigDecimal.valueOf(15.0)))
        val persistedSchedule = entityManager.persist(Schedule(movieId, 1, LocalTime.of(18, 0), BigDecimal.valueOf(20.5), BigDecimal.valueOf(15.0)))
        val expectation = listOf(persistedSchedule)

        val result = scheduleRepository.findByMovieId(movieId)

        assertEquals(expectation, result)
    }
}