package com.danielcsant.metrics;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kohsuke.github.GHCommit;

import java.io.IOException;

public class GitHubServiceIntegrationTest {

    @Test
    @Ignore
    public void getOldestCommit() throws IOException {

        GitHubService gitHubService = new GitHubService(AppProperties.getProperty("oauthAccessToken"), "apache/spark");

        GHCommit ghCommit = gitHubService.getOldestCommit();

        Assert.assertNotNull(ghCommit);
        Assert.assertEquals("Tue Feb 25 08:20:38 CET 2014", ghCommit.getCommitDate().toString());
    }

}