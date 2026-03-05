package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;

import java.nio.file.Path;

public class FileUserRepository extends FileRepository<User> implements UserRepository {
    public FileUserRepository(Path path) {
        super(path);
    }

    @Override
    protected User parseLine(String line) {
        String[] fileFields = line.split(";");

        // Getting the fields of the current user:
        long id = Long.parseLong(fileFields[0]);
        String name = fileFields[1];
        String email = fileFields[2];

        return new User(id, name, email);
    }

    @Override
    protected String convertObjectToFileLine(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getEmail() + ";" +
                user.formatDate();
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