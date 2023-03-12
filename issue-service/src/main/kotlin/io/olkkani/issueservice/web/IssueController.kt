package io.olkkani.issueservice.web

import io.olkkani.issueservice.config.AuthUser
import io.olkkani.issueservice.model.IssueRequest
import io.olkkani.issueservice.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController (
    private val issueService: IssueService,
){
    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ) = issueService.create(authUser.userId, request)
}