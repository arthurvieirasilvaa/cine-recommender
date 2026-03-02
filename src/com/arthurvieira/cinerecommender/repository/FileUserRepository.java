package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.domain.User;
import com.arthurvieira.cinerecommender.exception.UserNotExistException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUserRepository implements UserRepository {
    private final Path path;
    private final Map<Long, User> users;
    private long nextId;

    public FileUserRepository(Path path, Map<Long, User> users) {
        this.path = path;
        this.users = loadFromFile();
        this.nextId = generateNextId();
    }

    private Map<Long, User> loadFromFile() {
        ensureFileExists();

        Map<Long, User> fileUsers = new LinkedHashMap<>();

        try (FileReader fileReader = new FileReader(this.path.toFile());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                User user = parseLine(line);
                fileUsers.put(user.getId(), user);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados do arquivo "+path.getFileName());
        }

        return fileUsers;
    }

    private void ensureFileExists() {
        try {
            Path parentDirectory = this.path.getParent();

            if(parentDirectory == null && Files.notExists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
            }

            if(Files.notExists(this.path)) {
                Files.createFile(this.path.getFileName());
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao preparar o arquivo de dados "+this.path.getFileName()+"!");
        }
    }

    private User parseLine(String line) {
        String[] fileFields = line.split(";");

        // Getting the fields of the current user:
        long id = Long.parseLong(fileFields[0]);
        String name = fileFields[1];
        String email = fileFields[2];

        return new User(id, name, email);
    }

    private String convertUserToFileLine(User user) {
        return user.getId() + ";" +
                user.getName() + ";" +
                user.getEmail() + ";" +
                user.formatDate();
    }

    private void writeAllToFile() {
        try(FileWriter fileWriter = new FileWriter(this.path.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for(User user : this.users.values()) {
                bufferedWriter.write(convertUserToFileLine(user));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo " + this.path.getFileName());
        }
    }

    @Override
    public User save(User user) {
        // Checks if the user is the first to be written in the file:
        if (user.getId() == 0) {
            user.setId(this.nextId++);
        }

        this.users.put(user.getId(), user);
        writeAllToFile();

        return user;
    }

    @Override
    public void deleteById(long id) {
        if(this.users.remove(id) == null) {
            throw new UserNotExistException("O usuário de ID"+id+"não existe!");
        }

        writeAllToFile();
    }

    public long generateNextId() {
        long maxId = 0;
        for (Long id : this.users.keySet()) {
            if(id > maxId) {
                id = maxId;
            }
        }

        return maxId+1;
    }

    @Override
    public User findById(long id) {
        User user = this.users.get(id);

        if(user != null) {
            return user;
        }

        throw new UserNotExistException("O usuário de ID"+id+"não existe!");
    }

    @Override
    public List<User> listAll() {
        return new ArrayList<>(this.users.values());
    }
}
