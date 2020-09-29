package pl.mkaczara.smallcinema.review.rest.mapper

import org.springframework.stereotype.Component
import pl.mkaczara.smallcinema.review.repository.entity.Review
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO

@Component
class ReviewDTOMapper {

    fun map(dto: ReviewDTO): Review =
            Review(dto.id, dto.movieId, dto.rating)

    fun map(review: Review): ReviewDTO =
            ReviewDTO(review.id, review.movieId, review.rating)
}