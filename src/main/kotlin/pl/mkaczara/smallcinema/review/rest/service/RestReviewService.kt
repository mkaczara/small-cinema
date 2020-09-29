package pl.mkaczara.smallcinema.review.rest.service

import pl.mkaczara.smallcinema.review.rest.model.AvgRatingDTO
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO

interface RestReviewService {

    fun save(reviewDTO: ReviewDTO): ReviewDTO

    fun getAvgRating(movieId: Long): AvgRatingDTO
}