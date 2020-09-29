package pl.mkaczara.smallcinema.movie.logic.service.impl

import org.springframework.stereotype.Service
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.OmdbApiService

@Service
class OmdbApiServiceImpl : OmdbApiService {

    override fun fetchMovieDetails(movieId: Long): OmdbMovieDetails? {
        //TODO: fetch response using rest template and api token provided from properties
        return OmdbMovieDetails()
    }
}