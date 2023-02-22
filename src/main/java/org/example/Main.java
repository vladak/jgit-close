package org.example;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, GitAPIException {
        Path rootPath = Files.createTempDirectory("foo");
        Git gitRepo = Git.init().setDirectory(rootPath.toFile()).call();

        Path path = Path.of(rootPath.toString(), "foo.txt");
        Files.createFile(path);
        gitRepo.add().addFilepattern(path.getFileName().toString()).call();

        gitRepo.commit().
                setMessage("initial content").
                setAuthor("author", "author@example.com").
                call();

        gitRepo.close();
    }
}