package com.Checkerly.BackEnd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object CheckerlyApplication {
    fun main(args: Array<String?>?) {
        SpringApplication.run(CheckerlyApplication::class.java, args)
    }
}
