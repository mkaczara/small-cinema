package pl.mkaczara.smallcinema.review.repository.impl

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import pl.mkaczara.smallcinema.review.repository.entity.Review

interface ReviewRepository : CrudRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.movieId = :movieId")
    fun getAvgRating(@Param("movieId") movieId: Long): Double
}