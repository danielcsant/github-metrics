package es.daniel.github;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        try {
            String githubServer = "https://api.github.com";
            String oauthAccessToken = System.getenv("OAUTH_TOKEN");
            GitHub github = GitHub.connectUsingOAuth(githubServer, oauthAccessToken);

            GHRepository repository = github.getRepository("danielcsant/RabbitMQ-Receiver");
            GHRelease latestReleases = repository.getLatestRelease();

            GHCommitQueryBuilder queryBuilder = repository.queryCommits().from("527481d7bf7840f794b856ec753c5a7d147ee152");
            PagedIterable<GHCommit> commits = queryBuilder.list();
            Iterator<GHCommit> iterator = commits.iterator();

            while (iterator.hasNext()) {
                GHCommit commit = iterator.next();
//                System.out.println("Commit: " + commit.getSHA1() + ", info: " + commit.getCommitShortInfo().getMessage() + ", author: " + commit.getAuthor());
                System.out.println("Commit: " + commit.getSHA1() + " " + commit.getCommitDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
