package pl.mkaczara.smallcinema.movie.repository.impl

import org.springframework.data.repository.CrudRepository
import pl.mkaczara.smallcinema.movie.repository.entity.Movie

interface MovieRepository : CrudRepository<Movie, Long>