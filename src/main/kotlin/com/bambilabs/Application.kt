package com.bambilabs

import com.bambilabs.plugins.*
import com.bambilabs.repository.UserRepository
import com.bambilabs.routing.configureRouting
import com.bambilabs.service.JwtService
import com.bambilabs.service.UserService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    val jwtService = JwtService(this, userService)

    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService, jwtService)
}
