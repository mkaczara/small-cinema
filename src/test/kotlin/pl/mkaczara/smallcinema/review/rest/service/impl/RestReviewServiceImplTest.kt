package pl.mkaczara.smallcinema.review.rest.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.mkaczara.smallcinema.review.repository.entity.Review
import pl.mkaczara.smallcinema.review.repository.impl.ReviewRepository
import pl.mkaczara.smallcinema.review.rest.mapper.ReviewDTOMapper
import pl.mkaczara.smallcinema.review.rest.model.AvgRatingDTO
import pl.mkaczara.smallcinema.review.rest.model.ReviewDTO

@ExtendWith(MockKExtension::class)
class RestReviewServiceImplTest {

    @MockK
    lateinit var reviewRepository: ReviewRepository

    @MockK
    lateinit var reviewDTOMapper: ReviewDTOMapper

    @InjectMockKs
    lateinit var reviewService: RestReviewServiceImpl

    @Test
    fun `should save review`() {
        val inputReviewDTO = ReviewDTO(1L, 2)
        val inputReview = Review(1L, 2)
        val savedReview = Review(12L, 1L, 2)
        val expectation = ReviewDTO(12L, 1L, 2)
        every { reviewDTOMapper.map(inputReviewDTO) } returns inputReview
        every { reviewRepository.save(inputReview) } returns savedReview
        every { reviewDTOMapper.map(savedReview) } returns expectation

        val result = reviewService.save(inputReviewDTO)

        assertEquals(expectation, result)
    }

    @Test
    fun `should get avg rating for movie`() {
        val movieId = 12L
        val avg = 3.21
        val expectation = AvgRatingDTO(movieId, avg)
        every { reviewRepository.getAvgRating(movieId) } returns avg

        val result = reviewService.getAvgRating(movieId)

        assertEquals(expectation, result)
    }
}