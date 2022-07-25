package com.asad.geniusanalysis.service.File;

import com.asad.geniusanalysis.entity.FileData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Value("${files.path")
    private String filesPath;

    public Resource download(String fileName) {
        try {
            Path file = Paths.get(filesPath).resolve(fileName);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File is not legit.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FileData> list() {
        try {
            Path root = Paths.get(filesPath);

            if (Files.exists(root)) {
                return Files.walk(root, 1)
                        .filter(path -> !path.equals(root))
                        .filter(path -> path.toFile().isFile())
                        .collect(Collectors.toList())
                        .stream()
                        .map(this::pathToFileData)
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException("Files not able to be listed");
        }
    }

    private FileData pathToFileData(Path path) {
        FileData fileData = new FileData();
        String fileName = path.getFileName().toString();

        fileData.setFilename(fileName);

        try {
            fileData.setContentType(Files.probeContentType(path));
            fileData.setSize(Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

        return fileData;
    }

}