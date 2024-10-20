package com.bambilabs.service

import com.bambilabs.model.User
import com.bambilabs.repository.UserRepository
import java.util.UUID

class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User> =
        userRepository.findAll()

    fun findById(id: UUID): User? =
        userRepository.findById(id)

    fun findByUsername(username: String): User? =
        userRepository.findByUsername(username)

    fun save(user: User): User? {
        val foundUser = findByUsername(user.username)

        return if (foundUser == null) {
            userRepository.save(user)
            user
        } else null
    }
}