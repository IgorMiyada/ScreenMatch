package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;

public class TitleList {
    String name;
    List<Title> shows = new ArrayList<>();

    public TitleList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Title> getShows() {
        return shows;
    }
}
