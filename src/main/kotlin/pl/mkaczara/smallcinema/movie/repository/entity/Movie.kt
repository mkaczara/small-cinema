package pl.mkaczara.smallcinema.movie.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
class Movie(
        @Id
        @SequenceGenerator(name = "MOVIE_SEQUENCE", sequenceName = "MOVIE_ID_SEQ", initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_SEQUENCE")
        var id: Long?,
        var title: String,
        var imdbId: String
) {
    constructor(title: String, imdbId: String) : this(null, title, imdbId)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (title != other.title) return false
        if (imdbId != other.imdbId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + imdbId.hashCode()
        return result
    }
}