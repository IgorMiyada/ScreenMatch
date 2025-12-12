package br.com.alura.screenmatch.model;

public class Episode {
    private int number;
    private String name;
    private Series series;
    private int views;

    public int getNumber() {return number; }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Series getSerie() {
        return series;
    }

    public void setSerie(Series series) {
        this.series = series;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
