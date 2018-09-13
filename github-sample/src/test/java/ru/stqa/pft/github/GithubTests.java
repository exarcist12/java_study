package ru.stqa.pft.github;

import com.jcabi.github.*;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("3483d82b9a3253b72ee7033a27935ec53cd26afa");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("exarcist12", "java_study")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
