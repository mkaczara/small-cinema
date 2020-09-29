package pl.mkaczara.smallcinema.review.rest.model

import io.swagger.annotations.ApiModelProperty

class AvgRatingDTO(
        @ApiModelProperty("The movie id", required = true) val movieId: Long,
        @ApiModelProperty("The average rating for movie id", required = true) val avgRating: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AvgRatingDTO

        if (movieId != other.movieId) return false
        if (avgRating != other.avgRating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movieId.hashCode()
        result = 31 * result + avgRating.hashCode()
        return result
    }
}