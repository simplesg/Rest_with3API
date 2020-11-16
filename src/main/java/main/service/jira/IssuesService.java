package main.service.jira;

import main.dto.issue.Assignee;
import main.dto.issue.Issue;
import main.dto.issue.comment.Comment;
import main.dto.issue.issueUpdate.IssueUpdate;
import org.springframework.http.ResponseEntity;

public interface IssuesService {

   String createIssue(Issue issue);

   String deleteIssueById(String id);

   ResponseEntity<String> getIssuesAssignedToUser(String name);

   void assignIssueToUser(Assignee assignee,String issueIdOrKey);

   String editIssue(IssueUpdate issue, String id);

   String addComment(Comment comment, String id);

   String deleteCommentById(String id, String commentId);
}
