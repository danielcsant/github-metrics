# GitHub Metrics

GitHub Metrics is a tool for retrieving time-to-market metrics from GitHub.

### Run
If you are going to access a private GitHub repo you will need to [create a personal access token](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line). Copy this token and paste it inside the property files at **/src/java/resoures/config.properties**. Then, run the following commands.

```sh
$ mvn clean package
$ mvn exec:java -Dexec.mainClass="com.danielcsant.metrics.Main"
```

License
----

Apache License Version 2.0