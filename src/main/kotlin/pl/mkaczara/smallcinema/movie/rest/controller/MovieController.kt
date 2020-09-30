package pl.mkaczara.smallcinema.movie.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved movies")
    ])
    @GetMapping("/movie", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMovies(): MoviesDTO =
            movieService.getAll()

    @ApiOperation("Get details for movie with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved movie details"),
        ApiResponse(code = 404, message = "Either movie for given id doesn't exist or no movie details in OMDB")
    ])
    @GetMapping("/movie/detail/{movieId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMovieDetails(@PathVariable("movieId") movieId: Long): MovieDetailsDTO =
            movieService.getDetails(movieId)
}