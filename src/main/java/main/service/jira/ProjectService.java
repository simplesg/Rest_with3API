package main.service.jira;

import main.dto.project.Project;

import java.util.ArrayList;

public interface ProjectService {

    ArrayList getAllProjects();
    Project getProjectById(String id);
}
