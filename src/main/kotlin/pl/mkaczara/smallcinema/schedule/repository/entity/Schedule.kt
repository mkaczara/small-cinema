package pl.mkaczara.smallcinema.schedule.repository.entity

import java.math.BigDecimal
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
class Schedule(
        @Id
        @SequenceGenerator(name = "SCHEDULE_SEQUENCE", sequenceName = "SCHEDULE_ID_SEQ", initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQUENCE")
        var id: Long?,
        var movieId: Long,
        var dayOfWeek: Int,
        var time: LocalTime,
        var normalPrice: BigDecimal,
        var discountPrice: BigDecimal
) {
    constructor(movieId: Long, dayOfWeek: Int, time: LocalTime, normalPrice: BigDecimal, discountPrice: BigDecimal) : this(
            null, movieId, dayOfWeek, time, normalPrice, discountPrice
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Schedule

        if (id != other.id) return false
        if (movieId != other.movieId) return false
        if (dayOfWeek != other.dayOfWeek) return false
        if (time != other.time) return false
        if (normalPrice != other.normalPrice) return false
        if (discountPrice != other.discountPrice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + movieId.hashCode()
        result = 31 * result + dayOfWeek
        result = 31 * result + time.hashCode()
        result = 31 * result + normalPrice.hashCode()
        result = 31 * result + discountPrice.hashCode()
        return result
    }

    override fun toString(): String {
        return "Schedule(id=$id, movieId=$movieId, dayOfWeek=$dayOfWeek, time=$time, normalPrice=$normalPrice, discountPrice=$discountPrice)"
    }

}