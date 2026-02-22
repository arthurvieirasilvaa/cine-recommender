package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.AgeRating;
import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.ContentType;
import com.arthurvieira.cinerecommender.domain.Genre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileContentRepository implements ContentRepository {
    private final Path path;
    private final List<Content> contents;
    private final long nextId;

    public FileContentRepository(Path path) {
        this.path = path;
        this.contents = loadFromFile();
        this.nextId = generateId();
    }

    public List<Content> loadFromFile() {
        try(FileReader fileReader = new FileReader(path.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados do arquivo "+path.getFileName());
        }

        return null;
    }

    @Override
    public Content save(Content content) {
        return null;
    }

    @Override
    public void delete(Content content) {

    }

    @Override
    public long generateId() {
        long maxId = 0;
        for(Content content : contents) {
            if(content.getId() > maxId) {
                maxId = content.getId();
            }
        }

        return maxId;
    }

    @Override
    public List<Content> listByGenre(Genre genre) {
        return List.of();
    }

    @Override
    public List<Content> listByType(ContentType contentType) {
        return List.of();
    }

    @Override
    public List<Content> listByAgeRating(AgeRating ageRating) {
        return List.of();
    }
}
