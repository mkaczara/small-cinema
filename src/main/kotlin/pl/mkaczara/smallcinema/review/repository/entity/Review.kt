package pl.mkaczara.smallcinema.review.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Review(
        @Id @GeneratedValue var id: Long?,
        var movieId: Long,
        var rating: Int
) {
    constructor(movieId: Long, rating: Int) : this(null, movieId, rating)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Review

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
}
