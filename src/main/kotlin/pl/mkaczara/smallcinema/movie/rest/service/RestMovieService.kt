package pl.mkaczara.smallcinema.movie.rest.service

import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO

interface RestMovieService {

    fun getAll(): MoviesDTO

    fun getDetails(movieId: Long): MovieDetailsDTO
}