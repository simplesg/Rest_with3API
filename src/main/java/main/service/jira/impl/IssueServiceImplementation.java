package main.service.jira.impl;

import lombok.extern.slf4j.Slf4j;
import main.dto.issue.Assignee;
import main.dto.issue.comment.Comment;
import main.dto.issue.issueUpdate.IssueUpdate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import main.dto.issue.CreateIssueResponse;
import main.dto.issue.Issue;
import main.exception.EmptyFieldException;
import main.service.jira.IssuesService;
import main.util.CheckIfObjectNullOrEmpty;

import static main.util.PageUri.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IssueServiceImplementation implements IssuesService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public String createIssue(Issue issue) {
        if (CheckIfObjectNullOrEmpty.checkIfIssueFieldsIsNullOrEmpty(issue)) {
            HttpEntity request = new HttpEntity(issue, getHeader());
            return restTemplate.exchange(JIRA_BASE_URL + CREATE_ISSUE, HttpMethod.POST, request,
                    CreateIssueResponse.class).getBody().getId();
        } else {
            try {
                throw new EmptyFieldException("Please fill all necessary fields");
            } catch (HttpClientErrorException httpClientErrorException) {
                return "You are not authorized, please sign in with your JIRA account";
            } catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String deleteIssueById(String id) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + DELETE_ISSUE.concat(id),
                    HttpMethod.DELETE, request,
                    String.class);
            return "Issue with id - " + id + " was deleted successfully";
        } catch (HttpClientErrorException clientErrorException) {
            return "You don't have permission to delete this issue";
        } catch (Exception e) {
            return "No Issue with ID " + id;
        }
    }

    @Override
    public ResponseEntity<String> getIssuesAssignedToUser(String name) {
        String url = JIRA_BASE_URL.concat(GET_ALL_ISSUES_ASSIGNED_TO_USER).concat(name);
        HttpEntity request = new HttpEntity<>(getHeader());
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return result;
    }

    @Override
    public void assignIssueToUser(Assignee assignee, String issueIdOrKey) {
        String url = JIRA_BASE_URL.concat(CREATE_ISSUE).concat(issueIdOrKey).concat(ASSIGNEE);
        HttpEntity request = new HttpEntity(assignee, getHeader());
        restTemplate.exchange(url, HttpMethod.PUT, request,
                String.class).getBody();
    }

    @Override
    public String editIssue(IssueUpdate issue, String id) {
        HttpEntity request = new HttpEntity(issue, getHeader());
        restTemplate.exchange(JIRA_BASE_URL.concat(CREATE_ISSUE).concat(id), HttpMethod.PUT, request, String.class);
        return "Issue was changed";

    }

    @Override
    public String addComment(Comment comment, String id) {
        HttpEntity request = new HttpEntity(comment, getHeader());
        restTemplate.exchange(JIRA_BASE_URL.concat(CREATE_ISSUE).concat(id).concat(COMMENT),HttpMethod.POST,request,String.class);
        return "Comment was added";
    }

    @Override
    public String deleteCommentById(String id, String commentId) {
        HttpEntity request = new HttpEntity(getHeader());
        restTemplate.exchange(JIRA_BASE_URL + DELETE_ISSUE.concat(id).concat(COMMENT).concat("/").concat(commentId),
                HttpMethod.DELETE, request,
                String.class);
        return "Comment with id - " + commentId + " was deleted successfully";

    }

    private HttpHeaders getHeader() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        return headers;
    }

}
