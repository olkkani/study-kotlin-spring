package io.olkkani.userservice.domain.model

import io.olkkani.userservice.domain.entity.User
import java.time.LocalDateTime


data class UserEditRequest(
    val username: String,
)
data class MeResponse(
    val id: Long,
    val profileUrl: String?,
    val username: String,
    val email: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?

)

fun User.toResponse() = MeResponse(
    id = id!!,
    profileUrl = if (profileUrl.isNullOrEmpty()) null else "http://localhost:9090/imges/$profileUrl",
    username = username,
    email = email,
    createdAt = createdAt, updatedAt = updatedAt,

)