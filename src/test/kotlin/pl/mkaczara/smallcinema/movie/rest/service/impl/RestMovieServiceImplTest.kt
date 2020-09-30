package pl.mkaczara.smallcinema.movie.rest.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verifyOrder
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.MovieService
import pl.mkaczara.smallcinema.movie.repository.entity.Movie
import pl.mkaczara.smallcinema.movie.rest.mapper.MovieDetailsDTOMapper
import pl.mkaczara.smallcinema.movie.rest.mapper.MoviesDTOMapper
import pl.mkaczara.smallcinema.movie.rest.model.MovieDTO
import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class RestMovieServiceImplTest {

    @MockK
    lateinit var movieService: MovieService

    @MockK
    lateinit var moviesDTOMapper: MoviesDTOMapper

    @MockK
    lateinit var movieDetailsDTOMapper: MovieDetailsDTOMapper

    @InjectMockKs
    lateinit var restMovieService: RestMovieServiceImpl

    @Test
    fun `should get all movies and map to rest DTO`() {
        val movies = setOf(Movie(12L, "Some movie", "tt12345"))
        val expectation = MoviesDTO(setOf(MovieDTO(12L, "Some movie")))
        every { movieService.getAll() } returns movies
        every { moviesDTOMapper.map(movies) } returns expectation

        val result = restMovieService.getAll()

        verifyOrder {
            movieService.getAll()
            moviesDTOMapper.map(movies)
        }
        assertEquals(expectation, result)
    }

    @Test
    fun `should get movie details and map to rest DTO`() {
        val movieId = 14L
        val movieDetails = MovieDetails("Some", "movie", LocalDate.now(), 9.0f, "106 min")
        val expectation = MovieDetailsDTO("Some", "movie", LocalDate.now(), 9.0f, "106 min")
        every { movieService.getDetails(movieId) } returns movieDetails
        every { movieDetailsDTOMapper.map(movieDetails) } returns expectation

        val result = restMovieService.getDetails(movieId)

        verifyOrder {
            movieService.getDetails(movieId)
            movieDetailsDTOMapper.map(movieDetails)
        }
        assertEquals(expectation, result)
    }
}