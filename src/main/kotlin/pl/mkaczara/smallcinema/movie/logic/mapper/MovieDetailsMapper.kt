package pl.mkaczara.smallcinema.movie.logic.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails

@Component
class MovieDetailsMapper {

    //TODO:
    fun map(omdbMovieDetails: OmdbMovieDetails): MovieDetails =
            MovieDetails()
}