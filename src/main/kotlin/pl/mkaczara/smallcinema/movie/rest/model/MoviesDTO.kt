package pl.mkaczara.smallcinema.movie.rest.model

import io.swagger.annotations.ApiModelProperty

class MoviesDTO(
        @ApiModelProperty("The list of all movies played by the cinema", required = true) val movies: Collection<MovieDTO>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MoviesDTO

        if (movies != other.movies) return false

        return true
    }

    override fun hashCode(): Int {
        return movies.hashCode()
    }

    override fun toString(): String {
        return "MoviesDTO(movies=$movies)"
    }

}