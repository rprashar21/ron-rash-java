package threads.CPUAndcores;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.stream.IntStream;

public class IoBoundDownloader {
    public static void main(String[] args) {
        String[] urls = IntStream.range(1, 101)
                .mapToObj(i -> "https://example.com/file" + i + ".dat")
                .toArray(String[]::new);

        // Spawn one thread per download
        for (String url : urls) {
            new Thread(() -> download(url)).start();
        }
    }

    static void download(String urlStr) {
        try (InputStream in = new URL(urlStr).openStream()) {
            Path out = Paths.get("cache", Paths.get(new URI(urlStr).getPath()).getFileName().toString());
            Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Saved " + out);
        } catch (Exception e) {
            System.err.println("Error downloading " + urlStr);
        }
    }
}

