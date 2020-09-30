package pl.mkaczara.smallcinema.movie.logic.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import pl.mkaczara.smallcinema.movie.logic.model.OmdbMovieDetails
import pl.mkaczara.smallcinema.movie.logic.service.OmdbApiService
import java.text.MessageFormat

@Service
class OmdbApiServiceImpl(
        val restTemplate: RestTemplate,
        @Value("\${small-cinema.omdbApi.key}") val ombdApiKey: String,
        @Value("\${small-cinema.omdbApi.urlTemplate}") val omdbApiUrlTemplate: String
) : OmdbApiService {

    @Cacheable("omdb_movie_details", key = "#ombdbMovieId", unless = "#result == null")
    override fun fetchMovieDetails(ombdbMovieId: String): OmdbMovieDetails? {
        val requestUrl = MessageFormat.format(omdbApiUrlTemplate, ombdApiKey, ombdbMovieId)
        val response = restTemplate.getForEntity<OmdbMovieDetails>(requestUrl)
        return response.body
    }
}