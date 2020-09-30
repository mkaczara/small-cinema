package pl.mkaczara.smallcinema.movie.rest.service.impl

import org.springframework.stereotype.Service
import pl.mkaczara.smallcinema.movie.logic.service.MovieService
import pl.mkaczara.smallcinema.movie.rest.mapper.MovieDetailsDTOMapper
import pl.mkaczara.smallcinema.movie.rest.mapper.MoviesDTOMapper
import pl.mkaczara.smallcinema.movie.rest.model.MovieDetailsDTO
import pl.mkaczara.smallcinema.movie.rest.model.MoviesDTO
import pl.mkaczara.smallcinema.movie.rest.service.RestMovieService

@Service
class RestMovieServiceImpl(
        val movieService: MovieService,
        val moviesDTOMapper: MoviesDTOMapper,
        val movieDetailsDTOMapper: MovieDetailsDTOMapper
) : RestMovieService {

    override fun getAll(): MoviesDTO =
            moviesDTOMapper.map(movieService.getAll())

    override fun getDetails(movieId: Long): MovieDetailsDTO =
            movieDetailsDTOMapper.map(movieService.getDetails(movieId))
}