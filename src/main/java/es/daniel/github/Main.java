package es.daniel.github;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String oauthAccessToken = System.getenv("OAUTH_TOKEN");

            GitHubService gitHubService = new GitHubService(oauthAccessToken, "apache/spark");
            gitHubService.printReleasesLeadTime();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
