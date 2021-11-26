package aoc2021;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public record PuzzleInput(String year, String day) {

    public final String asString() throws IOException, InterruptedException {
        var path = Path.of("./input/%s/day%s.txt".formatted(year(), day()));
        if (path.toFile().isFile()) {
            return Files.readString(path);
        }
        String fromSite = fetch();
        Files.writeString(path, fromSite);
        return fromSite;
    }

    private String fetch() throws IOException, InterruptedException {
        var uri = inputURI();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().GET().uri(uri).setHeader("Cookie", sessionCookie()).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("GET " + uri + " returned " + response.statusCode());
        }
        return response.body();
    }

    private URI inputURI() {
        return URI.create("https://adventofcode.com/%s/day/%s/input".formatted(year(), day()));
    }

    private String sessionCookie() throws IOException {
        return "session=%s".formatted(Files.readString(Path.of("./.aoc-session")).strip());
    }

    public static void main(String... args) throws Exception {
        var example = new PuzzleInput(args[0], args[1]);
        System.out.println(example.asString());
    }
}
