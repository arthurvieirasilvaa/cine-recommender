package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.ContentNotExistException;
import com.arthurvieira.cinerecommender.exception.UserNotExistException;

import java.nio.file.Path;

public class FileRatingRepository extends FileRepository<Rating> implements RatingRepository {
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public FileRatingRepository(Path path, ContentRepository contentRepository, UserRepository userRepository) {
        super(path);
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;

        this.objects = loadFromFile();
        this.nextId = generateNextId();
    }

    @Override
    protected Rating parseLine(String line) {
        try {
            String[] ratingFields = line.split(";");

            if(ratingFields.length != 4) {
                throw new IllegalArgumentException("A linha do arquivo de avaliações está inválida!");
            }

            // Getting the fields of the current rating:
            long id = Long.parseLong(ratingFields[0]);
            long userId = Long.parseLong(ratingFields[1]);
            long contentId = Long.parseLong(ratingFields[2]);
            int stars = Integer.parseInt(ratingFields[3]);

            User user = this.userRepository.findById(userId);

            if(user == null) {
                throw new UserNotExistException("O usuário com o ID "+userId+" não existe!");
            }

            Content content = this.contentRepository.findById(contentId);

            if(content == null) {
                throw new ContentNotExistException("O conteúdo com o ID "+contentId+" não existe!");
            }

            return new Rating(id, user, content, stars);
        } catch (Exception e) {
            System.out.println("Ocorreu ao processar a linha: "+line);
            return null;
        }
    }

    @Override
    protected String convertObjectToFileLine(Rating rating) {
        return rating.getId() + ";" +
                rating.getUser().getId() + ";" +
                rating.getContent().getId() + ";" +
                rating.getStars();
    }

    @Override
    protected long getObjectId(Rating rating) {
        return rating.getId();
    }

    @Override
    protected void setObjectId(Rating rating, long id) {
        rating.setId(id);
    }
}