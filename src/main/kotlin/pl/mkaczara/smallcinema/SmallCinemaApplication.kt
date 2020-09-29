package pl.mkaczara.smallcinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmallCinemaApplication

fun main(args: Array<String>) {
    runApplication<SmallCinemaApplication>(*args)
}

