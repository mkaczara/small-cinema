package pl.mkaczara.smallcinema.common.rest.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class ErrorResponse(val message: String?,
                    val code: String,
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss") val timestamp: LocalDateTime
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ErrorResponse

        if (message != other.message) return false
        if (code != other.code) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}