package com.danielcsant.metrics;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class GitHubService {

    private static final Logger LOG = Logger.getLogger(GitHubService.class.getCanonicalName());

    private final String GITHUB_SERVER = "https://api.github.com";
    private GHRepository repository;

    public GitHubService(String oauthAccessToken, String repositoryURL) throws IOException {
        GitHub github = GitHub.connectUsingOAuth(GITHUB_SERVER, oauthAccessToken);
        repository = github.getRepository(repositoryURL);
    }

    public void printReleasesLeadTime() throws IOException {
        List<GHTag> tags = repository.listTags().asList();
        Collections.reverse(tags);
        GHCommit firstCommit = getOldestCommit();
        Date startDate = firstCommit.getCommitDate();
        for (GHTag tag : tags) {
            if (!tag.getName().toUpperCase().contains("RC")){
                long days = TimeUnit.MILLISECONDS
                        .toDays(Math.abs(startDate.getTime() - tag.getCommit().getCommitDate().getTime()));
                System.out.println("Release: " + tag.getName() + " with Release Lead Time: " + days + " days");

                startDate = tag.getCommit().getCommitDate();
            }
        }
    }

    public GHCommit getOldestCommit() throws IOException {
        GHCommit oldestCommit = null;

        try {
            Date createdRepositoryAt = repository.getCreatedAt();
            LOG.info(createdRepositoryAt.toString());
            oldestCommit = repository.queryCommits()
                    .until(createdRepositoryAt.getTime())
                    .list().withPageSize(1).iterator().next();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return oldestCommit;
    }
}
