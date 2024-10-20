package com.bambilabs.routing

import com.bambilabs.routing.request.LoginRequest
import com.bambilabs.service.JwtService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoute(
    jwtService: JwtService
) {
    post {
        val logginRequest = call.receive<LoginRequest>()

        val token = jwtService.createJwtToken(logginRequest)

        token?.let {
            call.respond(hashMapOf("token" to it))
        } ?: call.respond(HttpStatusCode.Unauthorized)
    }
}