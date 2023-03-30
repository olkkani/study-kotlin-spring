package io.olkkani.userservice.domain.service

import io.olkkani.userservice.domain.entity.User
import io.olkkani.userservice.domain.model.SignUpRequest
import io.olkkani.userservice.domain.repository.UserRepository
import io.olkkani.userservice.exception.UserExistsException
import io.olkkani.userservice.utils.BcryptUtils
import org.springframework.stereotype.Service

@Service
class UserService (
   private val userRepository: UserRepository,
) {
    suspend fun signUp(signUpRequest: SignUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistsException()
            }
            val user = User(
                email = email,
                password = BcryptUtils.hash(password),
                username = username,
            )

            userRepository.save(user)
        }
    }
}