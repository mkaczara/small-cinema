package pl.mkaczara.smallcinema.review.rest.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO

@RestController
@RequestMapping("/api/v1")
class ReviewController {

    @PostMapping(value = ["/review"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addReview(@RequestBody reviewDTO: ReviewDTO): ReviewDTO {
        return ReviewDTO(1L, 5);
    }
}
