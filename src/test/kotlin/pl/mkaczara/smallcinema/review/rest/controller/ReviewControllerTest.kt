package pl.mkaczara.smallcinema.review.rest.controller

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
import org.springframework.test.web.servlet.post
import pl.mkaczara.smallcinema.review.rest.model.AvgRatingDTO
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO
import pl.mkaczara.smallcinema.review.rest.service.RestReviewService

@WebMvcTest(ReviewController::class)
@ExtendWith(MockKExtension::class)
class ReviewControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var reviewService: RestReviewService

    @Test
    fun `should process add movie review request`() {
        val inputReviewDTO = ReviewDTO(1L, 5)
        val expectation = ReviewDTO(1L, 1L, 5)
        every { reviewService.save(inputReviewDTO) } returns expectation

        mockMvc.post("/api/v1/review") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(inputReviewDTO)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(mapper.writeValueAsString(expectation)) }
        }
    }

    @Test
    fun `should process avg movie rating request`() {
        val movieId = 343L
        val expectation = AvgRatingDTO(movieId, 3.45)
        every { reviewService.getAvgRating(movieId) } returns expectation

        mockMvc.get("/api/v1/review/".plus(movieId))
                .andExpect {
                    status { isOk }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { json(mapper.writeValueAsString(expectation)) }
                }
    }
}