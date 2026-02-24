package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.ContentNotExistException;
import com.arthurvieira.cinerecommender.exception.InvalidContentException;
import com.arthurvieira.cinerecommender.exception.InvalidContentTypeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class FileContentRepository implements ContentRepository {
    private final Path path;
    private final List<Content> contents;
    private long nextId;

    public FileContentRepository(Path path) {
        this.path = path;
        this.contents = loadFromFile();
        this.nextId = generateNextId();
    }

    private List<Content> loadFromFile() {
        List<Content> fileContents = new ArrayList<>();

        if(Files.notExists(this.path)) {
            return fileContents;
        }

        try(FileReader fileReader = new FileReader(path.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Content content = parseLine(line);
                fileContents.add(content);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados do arquivo "+path.getFileName());
        }

        return fileContents;
    }

    private Content parseLine(String line) {
        String[] contentfields = line.split(";");

        // Getting the fields of the current content:
        long id = Long.parseLong(contentfields[0]);
        ContentType type = ContentType.valueOf(contentfields[1]);
        String title = contentfields[2];
        Year year = Year.parse(contentfields[3]);
        Genre genre = Genre.valueOf(contentfields[4]);
        AgeRating ageRating = AgeRating.valueOf(contentfields[5]);

        if(type == ContentType.MOVIE) {
            Duration duration = Duration.ofMinutes(Long.parseLong(contentfields[6]));

            return new Movie(id, title, year, genre, ageRating, duration);
        }

        if(type == ContentType.SERIES) {
            int numberOfSeasons = Integer.parseInt(contentfields[6]);
            int totalEpisodes = Integer.parseInt(contentfields[7]);

            return new Series(id, title, year, genre, ageRating, numberOfSeasons, totalEpisodes);
        }

        throw new InvalidContentTypeException("O tipo do conteúdo "+type+" é inválido!");
    }

    private String convertContentToFileLine(Content content) {
        if(content instanceof Movie) {
            return content.getId() + ";" +
                    ContentType.MOVIE.getType() + ";" +
                    content.getTitle() + ";" +
                    content.getReleaseYear() + ";" +
                    content.getGenre() + ";" +
                    content.getAgeRating() + ";" +
                    ((Movie) content).getDuration().toMinutes();
        }

        if(content instanceof Series) {
            return content.getId() + ";" +
                    ContentType.SERIES.getType() + ";" +
                    content.getTitle() + ";" +
                    content.getReleaseYear() + ";" +
                    content.getGenre() + ";" +
                    content.getAgeRating() + ";" +
                    ((Series) content).getNumberOfSeasons() + ";" +
                    ((Series) content).getTotalEpisodes();
        }

        throw new InvalidContentException("A obra precisa ser um filme ou uma série!");
    }

    private void writeAllToFile() {
        try(FileWriter fileWriter = new FileWriter(path.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for(Content content : this.contents) {
                bufferedWriter.write(convertContentToFileLine(content));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo " + path.getFileName());
        }
    }

    @Override
    public Content save(Content content) {
        // Checks if the content is the first to be written in the file:
        if(content.getId() == 0) {
            content.setId(this.nextId++);
            this.contents.add(content);
        }

        else {
            // Delete a content in the file with the same ID
            // as the content passed (if exists):
            this.contents.removeIf(c -> c.getId() == content.getId());
            this.contents.add(content);
        }

        writeAllToFile();
        return content;
    }

    @Override
    public void deleteById(long id) {
        boolean removed = this.contents.removeIf(c -> c.getId() == id);

        if(!removed) {
            throw new ContentNotExistException("A obra de ID "+id+"não existe!");
        }
        writeAllToFile();
    }

    public long generateNextId() {
        long maxId = 0;
        for(Content content : this.contents) {
            if(content.getId() > maxId) {
                maxId = content.getId();
            }
        }

        return maxId+1;
    }

    @Override
    public Content findById(long id) {
        for(Content content : this.contents) {
            if(content.getId() == id) {
                return content;
            }
        }

        throw new ContentNotExistException("A obra de ID "+id+"não existe!");
    }

    @Override
    public List<Content> findByGenre(Genre genre) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.contents) {
            if(content.getGenre() == genre) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }

    @Override
    public List<Content> findByType(ContentType contentType) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.contents) {
            if(content.getContentType() == contentType) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }

    @Override
    public List<Content> findByAgeRating(AgeRating ageRating) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.contents) {
            if(content.getAgeRating() == ageRating) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }
}