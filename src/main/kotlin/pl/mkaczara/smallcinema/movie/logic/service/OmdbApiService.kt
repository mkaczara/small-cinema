package pl.mkaczara.smallcinema.movie.logic.service

import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails

interface OmdbApiService {

    fun fetchMovieDetails(ombdbMovieId: String): OmdbMovieDetails?
}