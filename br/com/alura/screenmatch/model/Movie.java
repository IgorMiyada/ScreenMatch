package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculate.Rating;

public class Movie extends Title implements Rating {
    @Override
    public int getRating() {
        return (int)super.getAverageRating()/ getTotalRatings();
    }

    public Movie(String nome, int anoDeLancamento) {
        super(nome, anoDeLancamento);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString(){
        return "Filme : " + this.getName() + "(" + this.getReleasedYear()+")";
    }
}
