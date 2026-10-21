package ua.edu.finance;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class FileDataSource implements DataSource {
    private final Path path;
    public FileDataSource(Path path) { this.path = path; }
    @Override public Reader open() throws IOException { return Files.newBufferedReader(path, StandardCharsets.UTF_8); }
}
