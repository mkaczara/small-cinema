package pl.mkaczara.smallcinema.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import pl.mkaczara.smallcinema.rest.model.MovieRatingDTO

@WebMvcTest(MovieController::class)
class MovieControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `should process movie rating request`() {
        val input = MovieRatingDTO(1L, 5);
        val expectation = MovieRatingDTO(1L, 5);

        mockMvc.post("/api/v1/movie/rating") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(input)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(mapper.writeValueAsString(expectation)) }
        }
    }
}