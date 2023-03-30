package io.olkkani.userservice.controller

import io.olkkani.userservice.domain.model.SignUpRequest
import io.olkkani.userservice.domain.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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

}