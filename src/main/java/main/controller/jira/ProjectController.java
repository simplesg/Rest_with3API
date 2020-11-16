package main.controller.jira;

import lombok.RequiredArgsConstructor;
import main.dto.project.Project;
import main.service.jira.impl.ProjectServiceImplementation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

import static main.util.PageUri.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROJECT)
public class ProjectController {

    private final ProjectServiceImplementation projectServiceImplementation;

    @GetMapping(ALL)
    public ResponseEntity<String> getAllProjects() {
        try {


            return ResponseEntity.ok("You currently assigned to the " +
                    projectServiceImplementation.getAllProjects().size() +
                    " projects");
        } catch (HttpClientErrorException e) {
            return ResponseEntity.badRequest().body("You don't have permission");
        }
    }

    @GetMapping(GET_BY_ID_PROJECT)
    public ResponseEntity<Project> getProjectById(@PathVariable String projectIdOrKey) {
        try {
            return ResponseEntity.ok(projectServiceImplementation.getProjectById(projectIdOrKey));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
