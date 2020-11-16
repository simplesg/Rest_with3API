package main.controller.jira;

import lombok.RequiredArgsConstructor;
import main.dto.issue.Assignee;
import main.dto.issue.Issue;
import main.dto.issue.comment.Comment;
import main.dto.issue.issueUpdate.IssueUpdate;
import main.service.jira.IssuesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static main.util.PageUri.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ISSUE)
public class IssueController {

    private final IssuesService issuesService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createIssue(@RequestBody Issue issue){
        return ResponseEntity.ok("Issue with ID " + issuesService.createIssue(issue) + " was created");
    }

    @DeleteMapping(DELETE)
    public String deleteIssueById(@PathVariable String id){
        return issuesService.deleteIssueById(id);
    }

    @GetMapping(ASSIGNED_TO_USER)
    public ResponseEntity<String> getIssuesAssignedToUser(@PathVariable String username){
        return issuesService.getIssuesAssignedToUser(username);
    }

    @PutMapping(ASSIGN_TO_USER)
    public ResponseEntity<String> assignIssueToUser(@RequestBody Assignee assignee,@PathVariable String issueIdOrKey){
        issuesService.assignIssueToUser(assignee,issueIdOrKey);
        return ResponseEntity.ok("Issue was assigned to " + assignee.getName());
    }

    @PutMapping(EDIT)
    public ResponseEntity<String> editIssue(@RequestBody IssueUpdate issue, @PathVariable String id){
        return ResponseEntity.ok(issuesService.editIssue(issue,id));
    }

    @PostMapping(ADD_COMMENT)
    public ResponseEntity<String> addComment(@RequestBody Comment comment, @PathVariable String id){
        return ResponseEntity.ok(issuesService.addComment(comment,id));
    }

    @DeleteMapping(DELETE_COMMENT)
    public ResponseEntity<String> deleteComment(@PathVariable String id,@PathVariable String commentId){
        return ResponseEntity.ok(issuesService.deleteCommentById(id, commentId));
    }
}
