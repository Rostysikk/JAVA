package ua.edu.finance;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
public class UrlDataSource implements DataSource {
    private final String url;
    public UrlDataSource(String url) { this.url = url; }
    @Override public Reader open() throws IOException {
        return new InputStreamReader(URI.create(url).toURL().openStream(), StandardCharsets.UTF_8);
    }
}
