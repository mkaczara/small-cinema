package pl.mkaczara.smallcinema.review.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import pl.mkaczara.smallcinema.review.rest.model.AvgRatingDTO
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO
import pl.mkaczara.smallcinema.review.rest.service.RestReviewService

@Api(description = "Operations related to movie reviews")
@RestController
@RequestMapping("/api/v1")
class ReviewController(
        val reviewService: RestReviewService
) {

    @ApiOperation("Add new movie review")
    @PostMapping("/review", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addReview(@RequestBody reviewDTO: ReviewDTO): ReviewDTO =
            reviewService.save(reviewDTO)

    @ApiOperation("Get average rating for movie with given id")
    @GetMapping("/review/{movieId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAvgRating(@PathVariable("movieId") movieId: Long): AvgRatingDTO =
            reviewService.getAvgRating(movieId)
}
