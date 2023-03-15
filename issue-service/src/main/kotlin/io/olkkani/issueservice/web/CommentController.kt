package io.olkkani.issueservice.web

import io.olkkani.issueservice.config.AuthUser
import io.olkkani.issueservice.model.CommentRequest
import io.olkkani.issueservice.model.CommentResponse
import io.olkkani.issueservice.service.CommentService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController (
    private val commentService: CommentService,
){
    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) : CommentResponse {
        return commentService.create(issueId,authUser.userId, authUser.username, request)
    }

}