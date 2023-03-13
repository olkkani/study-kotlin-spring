package io.olkkani.issueservice.service

import io.olkkani.issueservice.domain.Issue
import io.olkkani.issueservice.domain.IssueRepository
import io.olkkani.issueservice.domain.enums.IssueStatus
import io.olkkani.issueservice.model.IssueRequest
import io.olkkani.issueservice.model.IssueResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService (
    private val issueRepository: IssueRepository,
){
    @Transactional
    fun create(userId:Long, request: IssueRequest): IssueResponse {
        val issue = Issue (
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )
        return IssueResponse(issueRepository.save(issue))
    }
    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus)=
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map{IssueResponse(it)}

}