package pl.mkaczara.smallcinema.movie.rest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.OmdbApiService
import pl.mkaczara.smallcinema.movie.repository.entity.Movie
import pl.mkaczara.smallcinema.movie.repository.impl.MovieRepository
import pl.mkaczara.smallcinema.movie.rest.model.MovieDTO
import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Optional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MovieControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var movieRepository: MovieRepository

    @MockkBean
    lateinit var omdbApiService: OmdbApiService

    @Test
    fun `should get all movies`() {
        val movie = Movie(1L, "F&F", "tt12345")
        val expectation = MoviesDTO(setOf(MovieDTO(1L, "F&F")))
        every { movieRepository.findAll() } returns setOf(movie)

        mockMvc.get("/api/v1/movie")
                .andExpect {
                    status { isOk }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { json(mapper.writeValueAsString(expectation)) }
                }
    }

    @Test
    fun `should get movie details`() {
        val movieId = 1L
        val imdbMovieId = "tt12345"
        val movie = Movie(movieId, "F&F", imdbMovieId)
        val omdbMovieDetails = OmdbMovieDetails("F&F", "plot", "12 Apr 2014", "8.0", "106 min")
        val expectation = MovieDetailsDTO("F&F", "plot", LocalDate.parse("12 Apr 2014", DateTimeFormatter.ofPattern("dd MMM yyyy")), 8.0f, "106 min")
        every { movieRepository.findById(movieId) } returns Optional.of(movie)
        every { omdbApiService.fetchMovieDetails(imdbMovieId) } returns omdbMovieDetails

        mockMvc.get("/api/v1/movie/detail/".plus(movieId))
                .andExpect {
                    status { isOk }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    content { json(mapper.writeValueAsString(expectation)) }
                }
    }
}