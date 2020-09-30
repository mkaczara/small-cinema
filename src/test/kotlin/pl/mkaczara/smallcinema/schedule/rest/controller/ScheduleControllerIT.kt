package pl.mkaczara.smallcinema.schedule.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import pl.mkaczara.smallcinema.schedule.rest.model.ScheduleDTO
import pl.mkaczara.smallcinema.schedule.rest.model.SchedulesForMovieDTO
import pl.mkaczara.smallcinema.schedule.rest.service.RestScheduleService
import java.math.BigDecimal

@WebMvcTest(ScheduleController::class)
@ExtendWith(MockKExtension::class)
internal class ScheduleControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var restScheduleService: RestScheduleService

    @Test
    fun `should process schedules request`() {
        val movieId = 343L
        val expectation = SchedulesForMovieDTO(movieId, mapOf(1 to listOf(ScheduleDTO(1L, movieId, 1, "12:00", BigDecimal.valueOf(23), BigDecimal.valueOf(13)))))
        every { restScheduleService.getForMovie(movieId) } returns expectation

        mockMvc.get("/api/v1/schedule/".plus(movieId))
                .andExpect {
                    status { isOk }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { json(mapper.writeValueAsString(expectation)) }
                }
    }
}