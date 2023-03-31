package io.olkkani.userservice.domain.service

import io.olkkani.userservice.config.JWTProperties
import io.olkkani.userservice.domain.entity.User
import io.olkkani.userservice.domain.model.SignInRequest
import io.olkkani.userservice.domain.model.SignInResponse
import io.olkkani.userservice.domain.model.SignUpRequest
import io.olkkani.userservice.domain.repository.UserRepository
import io.olkkani.userservice.exception.PasswordNotMatchedException
import io.olkkani.userservice.exception.UserExistsException
import io.olkkani.userservice.exception.UserNotFoundException
import io.olkkani.userservice.utils.BcryptUtils
import io.olkkani.userservice.utils.JWTClaim
import io.olkkani.userservice.utils.JWTUtils
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwpProperties: JWTProperties,
    private val cacheManager: CoroutineCashManager<User>
) {

    companion object {
        private val CACHE_TTL = Duration.ofMinutes(1)
    }

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

    suspend fun signIn(signInRequest: SignInRequest) : SignInResponse{
       return with(userRepository.findByEmail(signInRequest.email) ?: throw UserNotFoundException()) {
            val verified = BcryptUtils.verity(signInRequest.password, password)
            if (!verified) {
                throw PasswordNotMatchedException()
            }

        val jwtClaim = JWTClaim(
            userId = id!!,
            email = email,
            profileUrl = profileUrl,
            username = username
        )
            val token = JWTUtils.createToken(jwtClaim, jwpProperties)
            cacheManager.awaitPut(key = token, value = this, ttl = CACHE_TTL)
            SignInResponse(
                email = email,
                username = username,
                token = token
            )
        }
    }

    suspend fun logout(token: String) {
        cacheManager.awaitEvict(token)

    }
}