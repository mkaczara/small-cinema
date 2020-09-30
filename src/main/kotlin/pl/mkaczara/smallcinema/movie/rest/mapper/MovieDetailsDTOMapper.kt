package pl.mkaczara.smallcinema.movie.rest.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO

@Component
class MovieDetailsDTOMapper {

    fun map(movieDetails: MovieDetails): MovieDetailsDTO =
            MovieDetailsDTO(
                    movieDetails.title,
                    movieDetails.description,
                    movieDetails.releaseDate,
                    movieDetails.imdbRating,
                    movieDetails.runtime
            )
}