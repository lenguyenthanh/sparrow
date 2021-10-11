package se.thanh.sparrow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
public open class App

public fun main(args: Array<String>) {
  runApplication<App>(*args)
}
