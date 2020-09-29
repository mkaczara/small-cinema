package pl.mkaczara.smallcinema.review.repository.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import pl.mkaczara.smallcinema.review.repository.entity.Review

@DataJpaTest
class ReviewRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val reviewRepository: ReviewRepository
) {

    @Test
    fun `should get avg rating for movie id`() {
        val movieId = 12L
        val expectation = (3 + 1 + 3) * 1.0 / 3
        entityManager.persist(Review(movieId, 3))
        entityManager.persist(Review(movieId, 1))
        entityManager.persist(Review(movieId, 3))
        entityManager.persist(Review(movieId + 1, 3))

        val result = reviewRepository.getAvgRating(movieId)

        assertEquals(expectation, result)
    }
}