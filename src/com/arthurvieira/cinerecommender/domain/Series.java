package com.arthurvieira.cinerecommender.domain;

import com.arthurvieira.cinerecommender.util.ValidationUtils;

import java.time.Year;

public class Series extends Content {
    private final int numberOfSeasons;
    private final int totalEpisodes;

    public Series(long id, String title, Year releaseYear, Genre genre, AgeRating ageRating, int numberOfSeasons, int totalEpisodes) {
        super(id, title, releaseYear, genre, ageRating, ContentType.SERIES);

        ValidationUtils.validateSeriesNumberOfSeasons(numberOfSeasons);
        this.numberOfSeasons = numberOfSeasons;

        ValidationUtils.validateSeriesTotalEpisodes(totalEpisodes);
        this.totalEpisodes = totalEpisodes;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", ageRating=" + ageRating +
                ", contentType=" + contentType +
                ", ratings=" + ratings +
                ", numberOfSeasons=" + numberOfSeasons +
                ", totalEpisodes=" + totalEpisodes +
                '}';
    }

    @Override
    public void printContent() {
        System.out.println("\n\t-> Série "+this.getTitle()+" com ID " + this.getId()+":\n"+
                "\t\t- Ano de lançamento: "+this.getReleaseYear()+"\n"+
                "\t\t- Gênero: "+this.getGenre().getName()+"\n"+
                "\t\t- Classificação Indicativa: "+this.getAgeRating().getDescription()+"\n"+
                "\t\t- Número de temporadas: "+this.getNumberOfSeasons()+"\n"+
                "\t\t- Total de episódios: "+this.getTotalEpisodes());
    }

    @Override
    public void printSummary() {
        System.out.println("\t\t - Série: "+this.getTitle());
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }
}