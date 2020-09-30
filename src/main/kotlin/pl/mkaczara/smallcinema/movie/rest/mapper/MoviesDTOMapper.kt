package pl.mkaczara.smallcinema.movie.rest.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.movie.repository.entity.Movie
import pl.mkaczara.smallcinema.movie.rest.model.MovieDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO
import java.util.stream.Collectors

@Component
class MoviesDTOMapper {

    fun map(movies: Collection<Movie>): MoviesDTO {
        return MoviesDTO(
                movies.stream().map { movie -> MovieDTO(movie.id, movie.title) }.collect(Collectors.toSet())
        )
    }
}