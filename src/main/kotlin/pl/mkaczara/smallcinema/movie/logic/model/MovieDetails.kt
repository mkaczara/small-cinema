package pl.mkaczara.smallcinema.movie.logic.model

import java.time.LocalDate

class MovieDetails(
        val title: String,
        val description: String,
        val releaseDate: LocalDate,
        val imdbRating: Float,
        val runtime: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDetails

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

    override fun toString(): String {
        return "MovieDetails(title='$title', description='$description', releaseDate=$releaseDate, imdbRating=$imdbRating, runtime='$runtime')"
    }


}