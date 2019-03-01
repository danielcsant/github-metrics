/**
 * Copyright © 2019 Daniel (danielcarrozasantana@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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