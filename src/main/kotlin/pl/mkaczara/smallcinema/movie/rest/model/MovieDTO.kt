package pl.mkaczara.smallcinema.movie.rest.model

import io.swagger.annotations.ApiModelProperty

class MovieDTO(
        @ApiModelProperty("The auto-generated movie id") val id: Long?,
        @ApiModelProperty("The movie title", required = true) val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDTO

        if (id != other.id) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }
}
