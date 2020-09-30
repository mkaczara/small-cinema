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
        entityManager.persist(Schedule(2L, 1, LocalTime.of(15, 0), BigDecimal.valueOf(20.5), BigDecimal(15.0)))
        val persistedExpectation = entityManager.persist(Schedule(2L, 1, LocalTime.of(12, 0), BigDecimal.valueOf(20.5), BigDecimal(15.0)))

        val result = scheduleRepository.findByDayOfWeekAndTime(1, LocalTime.of(12, 0))

        assertEquals(persistedExpectation, result)
    }

    @Test
    fun `should find by movie id`() {
        entityManager.persist(Schedule(3L, 1, LocalTime.of(15, 0), BigDecimal.valueOf(20.5), BigDecimal(15.0)))
        val persistedSchedule = entityManager.persist(Schedule(2L, 1, LocalTime.of(12, 0), BigDecimal.valueOf(20.5), BigDecimal(15.0)))
        val expectation = listOf(persistedSchedule)

        val result = scheduleRepository.findByMovieId(2L)

        assertEquals(expectation, result)
    }
}