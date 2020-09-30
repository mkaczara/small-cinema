package pl.mkaczara.smallcinema.schedule.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import pl.mkaczara.smallcinema.schedule.repository.entity.Schedule
import pl.mkaczara.smallcinema.schedule.repository.impl.ScheduleRepository
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import java.math.BigDecimal
import java.time.LocalTime
import java.util.Optional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ScheduleAdminControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var scheduleRepository: ScheduleRepository

    @Test
    @WithMockUser(roles = ["admin"])
    fun `should add schedule`() {
        val dayOfWeek = 1
        val time = LocalTime.parse("12:00")
        val inputScheduleDTO = ScheduleDTO(2L, dayOfWeek, "12:00", BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val inputSchedule = Schedule(2L, dayOfWeek, time, BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val outputSchedule = Schedule(1L, 2L, dayOfWeek, time, BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val expectation = ScheduleDTO(1L, 2L, dayOfWeek, "12:00", BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns null
        every { scheduleRepository.save(inputSchedule) } returns outputSchedule

        mockMvc.post("/api/v1/admin/schedule") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(inputScheduleDTO)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            authenticated()
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(mapper.writeValueAsString(expectation)) }
        }
    }

    @Test
    @WithMockUser(roles = ["admin"])
    fun `should update schedule`() {
        val dayOfWeek = 1
        val time = LocalTime.parse("12:00")
        val inputScheduleDTO = ScheduleDTO(1L, 2L, dayOfWeek, "12:00", BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val inputSchedule = Schedule(1L, 2L, dayOfWeek, time, BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val expectation = inputScheduleDTO
        every { scheduleRepository.findByDayOfWeekAndTime(dayOfWeek, time) } returns null
        every { scheduleRepository.save(inputSchedule) } returns inputSchedule

        mockMvc.put("/api/v1/admin/schedule") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(inputScheduleDTO)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            authenticated()
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(mapper.writeValueAsString(expectation)) }
        }
    }

    @Test
    @WithMockUser(roles = ["admin"])
    fun `should delete schedule`() {
        val scheduleId = 122L
        val inputScheduleDTO = ScheduleDTO(scheduleId, 2L, 1, "12:00", BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val inputSchedule = Schedule(scheduleId, 2L, 1, LocalTime.parse("12:00"), BigDecimal.valueOf(24), BigDecimal.valueOf(12))
        val expectation = inputScheduleDTO
        every { scheduleRepository.findById(scheduleId) } returns Optional.of(inputSchedule)
        every { scheduleRepository.delete(inputSchedule) } returns Unit

        mockMvc.delete("/api/v1/admin/schedule/".plus(scheduleId)) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            authenticated()
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(mapper.writeValueAsString(expectation)) }
        }
    }
}