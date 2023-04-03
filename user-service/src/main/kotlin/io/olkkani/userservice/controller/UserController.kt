package io.olkkani.userservice.controller

import io.olkkani.userservice.domain.entity.User
import io.olkkani.userservice.domain.model.*
import io.olkkani.userservice.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController (
    private val userService: UserService
){

    @PostMapping("/signup")
    suspend fun signUp(@RequestBody request: SignUpRequest){
        userService.signUp(request)
    }

    @PostMapping("/signin")
    suspend fun signIn(@RequestBody signInRequest: SignInRequest) : SignInResponse {
       return userService.signIn(signInRequest)
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun logout(@AuthToken token: String){
        userService.logout(token)
    }

    @GetMapping("/me")
    suspend fun get(
        @AuthToken token: String
    ): MeResponse {
        return userService.getByToken(token).toResponse()
    }
}