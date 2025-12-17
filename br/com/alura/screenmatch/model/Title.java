package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.excession.ErroDeConversaoDeAnoException;

public class Title implements Comparable<Title>{
    private String name;
    private int releasedYear;
    private boolean includedOnPlan;
    private double averageRating;
    private int totalRatings;
    private int runtimeInMinutes;
    private String director;

    public Title(String name, int releasedYear) {
        this.name = name;
        this.releasedYear = releasedYear;
    }

    public Title(OmdbTitle omdbTitle){
        this.name = omdbTitle.Title();
        if(omdbTitle.Year() == null || omdbTitle.Year().length()>4 ){
            throw new ErroDeConversaoDeAnoException("Erro ao tentar obter o ano. Campo ano contém mais de 4 caracteres");
        }
        this.releasedYear = Integer.parseInt(omdbTitle.Year());
        this.runtimeInMinutes = Integer.parseInt(omdbTitle.Runtime().substring(0,2));
    }

    @Override
    public int compareTo(Title outroTitle) {
        return this.getName().compareTo(outroTitle.getName());
    }

    public void avaliar(double nota) {
        this.averageRating += nota;
        this.totalRatings++;
    }

    public void exibeFichaTecnica() {
        String sinopse = """
                Titulo : %s
                Nota do title : %.2f
                Ano do title : %d
                Duração : %s
                ######""".formatted(this.name, (this.averageRating /this.totalRatings), this.releasedYear, this.runtimeInMinutes);
        System.out.println(sinopse);
    }

    public double exibeMediaAvaliacoes() {
        return this.averageRating / this.totalRatings;
    }

    public String getName() {
        return name;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public boolean isIncludedOnPlan() {
        return includedOnPlan;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setIncludedOnPlan(boolean includedOnPlan) {
        this.includedOnPlan = includedOnPlan;
    }

    public void setRuntimeInMinutes(int runtimeInMinutes) {
        this.runtimeInMinutes = runtimeInMinutes;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setName(String name) {
        this.name = name;
    }
}


