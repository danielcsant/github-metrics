package es.daniel.github;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        try {
            String githubUrl = "https://api.github.com/api/v3/";
            String token = "9258b2be27eff89d1eca6f9f4e025df87e8e2bc4";
            GitHub github = GitHub.connectUsingPassword("", "");

            GHRepository repository = github.getRepository("danielcsant/RabbitMQ-Receiver");

            GHCommitQueryBuilder queryBuilder = repository.queryCommits().from("527481d7bf7840f794b856ec753c5a7d147ee152");
            PagedIterable<GHCommit> commits = queryBuilder.list();
            Iterator<GHCommit> iterator = commits.iterator();

            while (iterator.hasNext()) {
                GHCommit commit = iterator.next();
//                System.out.println("Commit: " + commit.getSHA1() + ", info: " + commit.getCommitShortInfo().getMessage() + ", author: " + commit.getAuthor());
                System.out.println("Commit: " + commit.getSHA1());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
