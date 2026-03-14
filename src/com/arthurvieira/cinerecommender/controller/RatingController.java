package com.arthurvieira.cinerecommender.controller;

import com.arthurvieira.cinerecommender.domain.Content;
import com.arthurvieira.cinerecommender.domain.Rating;
import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.InvalidIdException;
import com.arthurvieira.cinerecommender.exception.ObjectNotExistException;
import com.arthurvieira.cinerecommender.service.ContentService;
import com.arthurvieira.cinerecommender.service.RatingService;
import com.arthurvieira.cinerecommender.service.UserService;
import com.arthurvieira.cinerecommender.ui.InputHandler;

public class RatingController {
    private final InputHandler inputHandler;
    private final RatingService ratingService;
    private final ContentService contentService;
    private final UserService userService;

    public RatingController(InputHandler inputHandler,
                            RatingService ratingService,
                            ContentService contentService,
                            UserService userService) {
        this.inputHandler = inputHandler;
        this.ratingService = ratingService;
        this.contentService = contentService;
        this.userService = userService;
    }

    void registerRating() {
        long userId = this.inputHandler.readPositiveInt("ID do usuário: ");
        long contentId = this.inputHandler.readPositiveInt("ID do conteúdo: ");
        int stars = this.inputHandler.readStars("Nota (1-5): ");

        try {
            User user = this.userService.filterUserById(userId);
            Content content = this.contentService.filterContentById(contentId);

            Rating rating = this.ratingService.createOrUpdateRating(user, content, stars);

            System.out.println("O usuário "+user.getName()+" avaliou o conteúdo "+content.getTitle()+" com a nota "+rating.getStars()+"/5");
        } catch (ObjectNotExistException e) {
            System.out.println("O id não existe!");
        }
    }

    void delete() {
        int id = this.inputHandler.readPositiveInt("ID: ");
        try {
            Rating rating = this.ratingService.deleteRating(id);
            System.out.println("A avaliação com ID "+rating.getId()+" do usuário "+rating.getUser().getName()+" e da obra "+rating.getContent().getTitle()+" foi removida com sucesso!");
        } catch (InvalidIdException e) {
            System.out.println("O ID informado está inválido!");
        } catch (ObjectNotExistException e) {
            System.out.println("A avaliação com ID "+id+" não existe!");
        }
    }
}
