package es.daniel.github;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

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
        List<GHRelease> releases = repository.listReleases().asList();
        Collections.reverse(releases);
        GHCommit firstCommit = repository.queryCommits().list().asList().get(repository.queryCommits().list().asList().size() - 1);
        Date startDate = firstCommit.getCommitDate();
        for (GHRelease release : releases) {
            if (!release.getName().toUpperCase().contains("RC")){
                long days = TimeUnit.MILLISECONDS.toDays(Math.abs(startDate.getTime() - release.getCreatedAt().getTime()));
                System.out.println("Release: " + release.getName() + " with Release Lead Time: " + days + " days");

                startDate = release.getCreatedAt();
            }
        }
    }

    public GHCommit getOldestCommit() {
        GHCommit oldestCommit = null;

        try {
            Date createdRepositoryAt = repository.getCreatedAt();
            LOG.info(createdRepositoryAt.toString());
            oldestCommit = repository.queryCommits()
                    .until(createdRepositoryAt.getTime())
                    .list().withPageSize(1).iterator().next();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return oldestCommit;
    }
}
