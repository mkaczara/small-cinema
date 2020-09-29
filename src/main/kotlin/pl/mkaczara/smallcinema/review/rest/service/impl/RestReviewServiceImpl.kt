package pl.mkaczara.smallcinema.review.rest.service.impl

import org.springframework.stereotype.Service
import pl.mkaczara.smallcinema.review.repository.impl.ReviewRepository
import pl.mkaczara.smallcinema.review.rest.mapper.ReviewDTOMapper
import pl.mkaczara.smallcinema.review.rest.model.AvgRatingDTO
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO
import pl.mkaczara.smallcinema.review.rest.service.RestReviewService

@Service
class RestReviewServiceImpl(
        val reviewRepository: ReviewRepository,
        val reviewDTOMapper: ReviewDTOMapper
) : RestReviewService {

    override fun save(reviewDTO: ReviewDTO): ReviewDTO =
            reviewDTOMapper.map(reviewRepository.save(reviewDTOMapper.map(reviewDTO)))

    override fun getAvgRating(movieId: Long): AvgRatingDTO =
            AvgRatingDTO(movieId, reviewRepository.getAvgRating(movieId))
}