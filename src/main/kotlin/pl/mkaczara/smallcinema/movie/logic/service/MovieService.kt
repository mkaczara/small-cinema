package pl.mkaczara.smallcinema.movie.logic.service

import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.repository.entity.Movie

interface MovieService {

    fun getAll(): Collection<Movie>

    fun getDetails(movieId: Long): MovieDetails
}