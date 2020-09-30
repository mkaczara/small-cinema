package pl.mkaczara.smallcinema.schedule.rest.model

import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

class ScheduleDTO(
        @ApiModelProperty("The auto-generated schedule id") val id: Long?,
        @ApiModelProperty("The movie id", required = true) val movieId: Long,
        @ApiModelProperty("The day of week (1-7)", required = true) val dayOfWeek: Int,
        @ApiModelProperty("The time (HH:mm)", required = true) val time: String,
        @ApiModelProperty("The normal price", required = true) val normalPrice: BigDecimal,
        @ApiModelProperty("The discount price", required = true) val discountPrice: BigDecimal
) {
    constructor(movieId: Long, dayOfWeek: Int, time: String, normalPrice: BigDecimal, discountPrice: BigDecimal) : this(
            null, movieId, dayOfWeek, time, normalPrice, discountPrice
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ScheduleDTO

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
        return "ScheduleDTO(id=$id, movieId=$movieId, dayOfWeek=$dayOfWeek, time='$time', normalPrice=$normalPrice, discountPrice=$discountPrice)"
    }

}