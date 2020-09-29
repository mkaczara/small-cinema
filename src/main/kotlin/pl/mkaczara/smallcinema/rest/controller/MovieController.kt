package pl.mkaczara.smallcinema.rest.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkaczara.smallcinema.rest.model.MovieRatingDTO

@RestController
@RequestMapping("/api/v1/movie")
class MovieController {

    @PostMapping(value = ["/rating"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun rateMovie(@RequestBody movieRatingDTO: MovieRatingDTO): MovieRatingDTO {
        return MovieRatingDTO(1L, 5);
    }

}
