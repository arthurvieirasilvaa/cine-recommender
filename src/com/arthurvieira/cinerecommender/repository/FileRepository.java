package com.arthurvieira.cinerecommender.repository;

import com.arthurvieira.cinerecommender.exception.ObjectNotExistException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class FileRepository<T> implements CrudRepository<T> {
    protected final Path path;
    protected final Map<Long, T> objects;
    protected long nextId;

    public FileRepository(Path path) {
        this.path = path;
        this.objects = loadFromFile();
        this.nextId = generateNextId();
    }

    protected abstract T parseLine(String line);
    protected abstract String convertObjectToFileLine(T object);
    protected abstract long getObjectId(T object);
    protected abstract void setObjectId(T object, long id);

    private Map<Long, T> loadFromFile() {
        ensureFileExists();

        Map<Long, T> fileObjects = new LinkedHashMap<>();

        try(FileReader fileReader = new FileReader(this.path.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                T object = parseLine(line);
                fileObjects.put(getObjectId(object), object);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados do arquivo "+path.getFileName());
        }

        return fileObjects;
    }

    private void ensureFileExists() {
        try {
            Path parentDirectory = this.path.getParent();

            if(parentDirectory != null && Files.notExists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
            }

            if(Files.notExists(this.path)) {
                Files.createFile(this.path);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao preparar o arquivo de dados "+this.path.getFileName()+"!");
        }
    }

    protected void writeAllToFile() {
        try(FileWriter fileWriter = new FileWriter(this.path.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for(T object : this.objects.values()) {
                bufferedWriter.write(convertObjectToFileLine(object));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo " + this.path.getFileName());
        }
    }

    public T save(T object) {
        // Checks if the object is the first to be written in the file:
        if(getObjectId(object) == 0) {
            setObjectId(object, this.nextId++);
        }

        this.objects.put(getObjectId(object), object);
        writeAllToFile();

        return object;
    }

    public T deleteById(long id) {
        T object = this.objects.remove(id);

        if(object != null) {
            writeAllToFile();
            return object;
        }

        throw new ObjectNotExistException("O object de ID "+id+" não existe!");
    }

    public T findById(long id) {
        T object = this.objects.get(id);

        if(object != null) {
            return object;
        }

        throw new ObjectNotExistException("O objeto de ID "+id+" não existe!");
    }

    public List<T> listAll() {
        return new ArrayList<>(this.objects.values());
    }

    protected long generateNextId() {
        long maxId = 0;
        for(Long id : this.objects.keySet()) {
            if(id > maxId) {
                maxId = id;
            }
        }

        return maxId+1;
    }
}
