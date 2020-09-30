package pl.mkaczara.smallcinema.movie.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO
import pl.mkaczara.smallcinema.movie.rest.service.RestMovieService

@Api(description = "Operations related to movies")
@RestController
@RequestMapping("/api/v1")
class MovieController(
        val movieService: RestMovieService
) {

    @ApiOperation("Get all movies played by the cinema")
    @GetMapping("/movie", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMovies(): MoviesDTO {
        return movieService.getAll()
    }

    @ApiOperation("Get details for movie with given id")
    @GetMapping("/movie/detail/{movieId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMovieDetails(@PathVariable("movieId") movieId: Long): MovieDetailsDTO {
        return movieService.getDetails(movieId)
    }
}