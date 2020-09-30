package pl.mkaczara.smallcinema.movie.logic.service.impl

import org.springframework.stereotype.Service
import pl.mkaczara.smallcinema.movie.logic.exception.MovieDetailsNotFoundException
import pl.mkaczara.smallcinema.movie.logic.exception.MovieNotFoundException
import pl.mkaczara.smallcinema.movie.logic.mapper.MovieDetailsMapper
import pl.mkaczara.smallcinema.movie.logic.model.MovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.MovieService
import pl.mkaczara.smallcinema.movie.logic.service.OmdbApiService
import pl.mkaczara.smallcinema.movie.repository.entity.Movie
import pl.mkaczara.smallcinema.movie.repository.impl.MovieRepository
import java.util.function.Consumer

@Service
class MovieServiceImpl(
        val movieRepository: MovieRepository,
        val omdbApiService: OmdbApiService,
        val movieDetailsMapper: MovieDetailsMapper
) : MovieService {

    override fun getAll(): Collection<Movie> {
        val allMovies = HashSet<Movie>()
        movieRepository.findAll().forEach { movie -> allMovies.add(movie) }
        return allMovies
    }

    override fun getDetails(movieId: Long): MovieDetails {
        val movieById = movieRepository.findById(movieId)
        if (movieById.isPresent) {
            val omdbMovieDetails = omdbApiService.fetchMovieDetails(movieById.get().imdbId)
            if (omdbMovieDetails != null) {
                return movieDetailsMapper.map(omdbMovieDetails)
            }
            throw MovieDetailsNotFoundException("Movie details for id $movieId not found")
        }
        throw MovieNotFoundException("Movie for id $movieId not found")
    }
}