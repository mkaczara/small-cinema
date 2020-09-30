package pl.mkaczara.smallcinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SmallCinemaApplication

fun main(args: Array<String>) {
    runApplication<SmallCinemaApplication>(*args)
}

