package pl.mkaczara.smallcinema.movie.logic.service.impl

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails

@ExtendWith(MockKExtension::class)
class OmdbApiServiceImplTest {

    @MockK
    lateinit var restTemplate: RestTemplate

    lateinit var omdbApiService: OmdbApiServiceImpl

    @BeforeEach
    internal fun setUp() {
        omdbApiService = OmdbApiServiceImpl(restTemplate, "apiKey", "apiUrl")
    }

    @Test
    fun `should call OMDBApi to fetch movie details`() {
        val omdbMovieId = "tt1234567"
        val expectation = OmdbMovieDetails("Some", "movie", "12 Jan 2014", "1.0", "106 min")
        val responseEntity = ResponseEntity(expectation, HttpStatus.OK)
        every { restTemplate.getForEntity<OmdbMovieDetails>(any<String>()) } returns responseEntity

        val result = omdbApiService.fetchMovieDetails(omdbMovieId)

        Assertions.assertEquals(expectation, result)
    }
}