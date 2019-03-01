package com.danielcsant.metrics;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {

            // "apache/spark"
            GitHubService gitHubService =
                    new GitHubService(AppProperties.getProperty("oauthAccessToken"), "apache/kafka");
            gitHubService.printReleasesLeadTime();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
