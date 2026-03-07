package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.*;
import com.arthurvieira.cinerecommender.exception.InvalidContentException;
import com.arthurvieira.cinerecommender.exception.InvalidContentTypeException;

import java.io.*;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Year;
import java.util.*;

public class FileContentRepository extends FileRepository<Content> implements ContentRepository {
    public FileContentRepository(Path path) {
        super(path);
    }

    @Override
    protected Content parseLine(String line) {
        try {
            String[] contentFields = line.split(";");

            if(contentFields.length < 6 || contentFields.length > 8) {
                throw new IllegalArgumentException("A linha do arquivo de conteúdos está inválida!");
            }

            // Getting the fields of the current content:
            long id = Long.parseLong(contentFields[0]);
            ContentType type = ContentType.fromType(contentFields[1]);
            String title = contentFields[2];
            Year year = Year.parse(contentFields[3]);
            Genre genre = Genre.valueOf(contentFields[4]);
            AgeRating ageRating = AgeRating.valueOf(contentFields[5]);

            if(type == ContentType.MOVIE) {
                Duration duration = Duration.ofMinutes(Long.parseLong(contentFields[6]));

                return new Movie(id, title, year, genre, ageRating, duration);
            }

            if(type == ContentType.SERIES) {
                int numberOfSeasons = Integer.parseInt(contentFields[6]);
                int totalEpisodes = Integer.parseInt(contentFields[7]);

                return new Series(id, title, year, genre, ageRating, numberOfSeasons, totalEpisodes);
            }

            throw new InvalidContentTypeException("O tipo do conteúdo "+type+" é inválido!");
        } catch (Exception e) {
            System.out.println("Ocorreu ao processar a linha: "+line);
            return null;
        }
    }

    @Override
    protected String convertObjectToFileLine(Content content) {
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

    @Override
    public List<Content> findByGenre(Genre genre) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.objects.values()) {
            if(content.getGenre() == genre) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }

    @Override
    public List<Content> findByType(ContentType contentType) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.objects.values()) {
            if(content.getContentType() == contentType) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }

    @Override
    public List<Content> findByAgeRating(AgeRating ageRating) {
        List<Content> contentsFiltered = new ArrayList<>();

        for(Content content : this.objects.values()) {
            if(content.getAgeRating() == ageRating) {
                contentsFiltered.add(content);
            }
        }

        return contentsFiltered;
    }

    @Override
    protected long getObjectId(Content content) {
        return content.getId();
    }

    @Override
    protected void setObjectId(Content content, long id) {
        content.setId(id);
    }
}