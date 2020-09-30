package pl.mkaczara.smallcinema.movie.logic.model

import com.fasterxml.jackson.annotation.JsonProperty

class OmdbMovieDetails(
        @JsonProperty("Title") val title: String,
        @JsonProperty("Plot") val plot: String,
        @JsonProperty("Released") val released: String,
        @JsonProperty("imdbRating") val imdbRating: String,
        @JsonProperty("Runtime") val runtime: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OmdbMovieDetails

        if (title != other.title) return false
        if (plot != other.plot) return false
        if (released != other.released) return false
        if (imdbRating != other.imdbRating) return false
        if (runtime != other.runtime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + plot.hashCode()
        result = 31 * result + released.hashCode()
        result = 31 * result + imdbRating.hashCode()
        result = 31 * result + runtime.hashCode()
        return result
    }

    override fun toString(): String {
        return "OmdbMovieDetails(title='$title', plot='$plot', released='$released', imdbRating='$imdbRating', runtime='$runtime')"
    }
}