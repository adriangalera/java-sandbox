package com.gal.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ListPackageResources {

    public static void main(String[] args) throws URISyntaxException, IOException {
        readResourcesFromJar();
    }

    private static void readResourcesFromJar() throws URISyntaxException, IOException {
        final String fontsDirectory = "/files/";
        final URI filesDirectory = ListPackageResources.class.getResource(fontsDirectory).toURI();

        Path myPath;
        if (filesDirectory.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(filesDirectory, Collections.emptyMap());
            myPath = fileSystem.getPath(fontsDirectory);
        } else {
            myPath = Paths.get(filesDirectory);
        }

        Set<String> fontFiles = Files.walk(myPath, 1)
            .filter(Files::isRegularFile)
            .map(path -> path.toAbsolutePath().toString())
            .collect(Collectors.toSet());

        System.out.println(fontFiles);
    }
}
