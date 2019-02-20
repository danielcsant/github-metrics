package es.daniel.github;

import org.junit.Assert;
import org.junit.Test;
import org.kohsuke.github.GHCommit;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class GitHubServiceIntegrationTest {

    @Test
    public void getOldestCommit() throws IOException {
        String oauthAccessToken = System.getenv("OAUTH_TOKEN");

        GitHubService gitHubService = new GitHubService(oauthAccessToken, "apache/spark");

        GHCommit ghCommit = gitHubService.getOldestCommit();

        Assert.assertNotNull(ghCommit);
        Assert.assertEquals("Tue Feb 25 08:20:38 CET 2014", ghCommit.getCommitDate().toString());
    }

}