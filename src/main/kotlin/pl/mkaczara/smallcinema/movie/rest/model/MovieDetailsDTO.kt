package pl.mkaczara.smallcinema.movie.rest.model

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

class MovieDetailsDTO(
        @ApiModelProperty("The movie title", required = true) val title: String,
        @ApiModelProperty("The movie description", required = true) val description: String,
        @ApiModelProperty("The movie release date", required = true) val releaseDate: LocalDate,
        @ApiModelProperty("The movie rating in IMDb", required = true) val imdbRating: Float,
        @ApiModelProperty("The movie runtime", required = true) val runtime: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDetailsDTO

        if (title != other.title) return false
        if (description != other.description) return false
        if (releaseDate != other.releaseDate) return false
        if (imdbRating != other.imdbRating) return false
        if (runtime != other.runtime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + imdbRating.hashCode()
        result = 31 * result + runtime.hashCode()
        return result
    }
}