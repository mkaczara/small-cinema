package pl.mkaczara.smallcinema.movie.logic.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.mkaczara.smallcinema.movie.logic.exception.MovieDetailsNotFoundException
import pl.mkaczara.smallcinema.movie.logic.exception.MovieNotFoundException
import pl.mkaczara.smallcinema.movie.logic.mapper.MovieDetailsMapper
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.OmdbApiService
import pl.mkaczara.smallcinema.movie.repository.entity.Movie
import pl.mkaczara.smallcinema.movie.repository.impl.MovieRepository
import java.time.LocalDate
import java.util.Optional

@ExtendWith(MockKExtension::class)
class MovieServiceImplTest {

    @MockK
    lateinit var movieRepository: MovieRepository

    @MockK
    lateinit var omdbApiService: OmdbApiService

    @MockK
    lateinit var movieDetailsMapper: MovieDetailsMapper

    @InjectMockKs
    lateinit var movieService: MovieServiceImpl

    @Test
    fun `should get all movies using repository`() {
        val movie1 = Movie(5L, "F&F", "tt1234")
        val movie2 = Movie(6L, "F&F 2", "tt1235")
        every { movieRepository.findAll() } returns listOf(movie1, movie2)

        val result = movieService.getAll()

        Assertions.assertThat(result).containsExactlyInAnyOrder(movie1, movie2)
    }

    @Test
    fun `should get movie details`() {
        val movieId = 12L
        val imdbMovieId = "tt1234"
        val movie = Movie(movieId, "Some1", imdbMovieId)
        val omdbMovieDetails = OmdbMovieDetails("Some1", "movie", "12 Jan 2014", "1.0", "106 min")
        val expectation = MovieDetails("Some2", "movie", LocalDate.now(), 1.0f, "106 min")
        every { movieRepository.findById(movieId) } returns Optional.of(movie)
        every { omdbApiService.fetchMovieDetails(imdbMovieId) } returns omdbMovieDetails
        every { movieDetailsMapper.map(omdbMovieDetails) } returns expectation

        val result = movieService.getDetails(movieId)

        assertEquals(expectation, result)
    }

    @Test
    fun `should throw when getting movie details and movie doesn't exist`() {
        val movieId = 12L
        every { movieRepository.findById(movieId) } returns Optional.empty()

        org.junit.jupiter.api.Assertions.assertThrows(MovieNotFoundException::class.java) {
            movieService.getDetails(movieId)
        }
    }

    @Test
    fun `should throw when getting movie details and no movie details in OMDB`() {
        val movieId = 12L
        val imdbMovieId = "tt1234"
        val movie = Movie(movieId, "Some1", imdbMovieId)
        every { movieRepository.findById(movieId) } returns Optional.of(movie)
        every { omdbApiService.fetchMovieDetails(imdbMovieId) } returns null

        org.junit.jupiter.api.Assertions.assertThrows(MovieDetailsNotFoundException::class.java) {
            movieService.getDetails(movieId)
        }
    }
}