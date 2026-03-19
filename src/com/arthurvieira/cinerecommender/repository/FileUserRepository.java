package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

public class FileUserRepository extends FileRepository<User> implements UserRepository {
    public FileUserRepository(Path path) {
        super(path);
        this.objects = loadFromFile();
        this.nextId = generateNextId();
    }

    @Override
    protected Optional<User> parseLine(String line) {
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

            return Optional.of(new User(id, name, email, registrationDate));
        } catch (Exception e) {
            System.out.println("Ocorreu ao processar a linha: "+line);
            return Optional.empty();
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