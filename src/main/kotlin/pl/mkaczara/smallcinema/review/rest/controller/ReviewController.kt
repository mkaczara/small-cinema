package pl.mkaczara.smallcinema.review.rest.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully added movie review")
    ])
    @PostMapping("/review", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addReview(@RequestBody reviewDTO: ReviewDTO): ReviewDTO =
            reviewService.save(reviewDTO)

    @ApiOperation("Get average rating for movie with given id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Successfully retrieved average movie rating")
    ])
    @GetMapping("/review/{movieId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAvgRating(@PathVariable("movieId") movieId: Long): AvgRatingDTO =
            reviewService.getAvgRating(movieId)
}
