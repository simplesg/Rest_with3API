package main.controller.jira;

import lombok.RequiredArgsConstructor;
import main.dto.project.Project;
import main.service.jira.impl.ProjectServiceImplementation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok("You currently assigned to the " +
                                 projectServiceImplementation.getAllProjects().size() +
                                 " projects");
    }
}
