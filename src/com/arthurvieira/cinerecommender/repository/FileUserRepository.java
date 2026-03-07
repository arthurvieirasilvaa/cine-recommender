package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;

import java.nio.file.Path;
import java.time.LocalDate;

public class FileUserRepository extends FileRepository<User> implements UserRepository {
    public FileUserRepository(Path path) {
        super(path);
    }

    @Override
    protected User parseLine(String line) {
        try {
            String[] userFields = line.split(";");

            if(userFields.length != 4) {
                throw new IllegalArgumentException("A linha do arquivo de usuários está inválida!");
            }

            // Getting the fields of the current user:
            long id = Long.parseLong(userFields[0]);
            String name = userFields[1];
            String email = userFields[2];
            LocalDate registrationDate = LocalDate.parse(userFields[3]);

            return new User(id, name, email, registrationDate);
        } catch (Exception e) {
            System.out.println("Ocorreu ao processar a linha: "+line);
            return null;
        }
    }

    @Override
    protected String convertObjectToFileLine(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getEmail() + ";" +
                user.getRegistrationDate();
    }

    @Override
    protected long getObjectId(User user) {
        return user.getId();
    }

    @Override
    protected void setObjectId(User user, long id) {
        user.setId(id);
    }
}