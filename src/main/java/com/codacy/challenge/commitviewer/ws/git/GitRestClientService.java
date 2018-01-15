package com.codacy.challenge.commitviewer.ws.git;

import com.codacy.challenge.commitviewer.dto.GitCommitLog;
import com.codacy.challenge.commitviewer.dto.Project;
import com.codacy.challenge.commitviewer.util.UtilFile;
import com.codacy.challenge.commitviewer.util.UtilGitRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.function.BiFunction;

@Service
public class GitRestClientService implements IGitClientService {

    private final UtilFile utilFile;
    private final RestTemplate restTemplate;
    private final UtilGitRestClient utilGitRestClient;
    private BiFunction<Object, Project, GitCommitLog> objectArrayToGitCommitLogDto = (obj, project) -> {

        LinkedHashMap map  = (LinkedHashMap) obj;
        final GitCommitLog result = new GitCommitLog();
        result.setCommitId((String) map.get("sha"));
        final LinkedHashMap commitMap = (LinkedHashMap) map.get("commit");
        final LinkedHashMap committerMap = (LinkedHashMap) commitMap.get("committer");

        result.setAuthor(committerMap.get("name") + " - " + committerMap.get("email"));
        result.setCommitDate(Date.from(ZonedDateTime.parse((String) committerMap.get("date")).toInstant()));
        result.setComment((String) commitMap.get("message"));
        result.setProjectId(project.getProjectId());

        return result;
    };

    @Autowired
    public GitRestClientService(UtilGitRestClient utilGitRestClient, UtilFile utilFile) {
        this.utilGitRestClient = utilGitRestClient;
        this.utilFile = utilFile;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Collection<GitCommitLog> getGitCommitLogFromRestApi(String url, Project project){

        final String repository = getUtilFile().getAuthorAndRepoFromGitUrl(url);

        final ResponseEntity<Object[]> resonseEntity =
                getRestTemplate().getForEntity(getUtilGitRestClient().getBuildGitCommitLogUrl().apply(repository), Object[].class);
        Collection<GitCommitLog> result = new HashSet<>();
        for (Object obj : resonseEntity.getBody()) {
            result.add(objectArrayToGitCommitLogDto.apply(obj, project));
        }

        return result;
    }


    private RestTemplate getRestTemplate() {
        return restTemplate;
    }

    private UtilGitRestClient getUtilGitRestClient() {
        return utilGitRestClient;
    }

    private UtilFile getUtilFile() {
        return utilFile;
    }
}
