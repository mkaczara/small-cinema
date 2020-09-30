package pl.mkaczara.smallcinema.movie.logic.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class MovieDetailsMapper {

    fun map(omdbMovieDetails: OmdbMovieDetails): MovieDetails =
            MovieDetails(
                    omdbMovieDetails.title,
                    omdbMovieDetails.plot,
                    LocalDate.parse(omdbMovieDetails.released, DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    omdbMovieDetails.imdbRating.toFloat(),
                    omdbMovieDetails.runtime
            )
}