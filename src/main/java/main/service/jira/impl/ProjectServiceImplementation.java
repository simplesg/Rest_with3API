package main.service.jira.impl;

import lombok.RequiredArgsConstructor;
import main.dto.jira.SessionValue;
import main.service.jira.ProjectService;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;

import static main.util.PageUri.GET_ALL_PROJECTS;
import static main.util.PageUri.JIRA_BASE_URL;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public ArrayList getAllProjects() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(JIRA_BASE_URL + GET_ALL_PROJECTS, HttpMethod.GET, request, ArrayList.class)
            .getBody();
    }
}
