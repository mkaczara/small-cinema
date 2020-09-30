package pl.mkaczara.smallcinema.review.rest.model

import io.swagger.annotations.ApiModelProperty

class ReviewDTO(
        @ApiModelProperty("The auto-generated review id") val id: Long?,
        @ApiModelProperty("The movie id", required = true) val movieId: Long,
        @ApiModelProperty("The movie rating", required = true) val rating: Int
) {
    constructor(movieId: Long, rating: Int) : this(null, movieId, rating)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReviewDTO

        if (id != other.id) return false
        if (movieId != other.movieId) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + movieId.hashCode()
        result = 31 * result + rating
        return result
    }

    override fun toString(): String {
        return "ReviewDTO(id=$id, movieId=$movieId, rating=$rating)"
    }

}

