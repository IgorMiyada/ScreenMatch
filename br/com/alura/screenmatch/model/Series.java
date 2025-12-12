package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.calculate.Rating;

public class Series extends Title implements Rating {
    private int seasons;
    private int episodesPerSeason;
    private boolean airing;
    private int episodeDurationInMinutes;
    private int totalVisualizacoes;

    public Series(String nome, int anoDelancamento){
        super(nome,anoDelancamento);
    }

    @Override
    public String toString(){
        return "Série : " + this.getName() + "(" + this.getReleasedYear()+")";
    }

    @Override
    public int getRuntimeInMinutes(){
        return this.episodesPerSeason*this.episodeDurationInMinutes*seasons;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(int episodesPerSeason) {
        this.episodesPerSeason = episodesPerSeason;
    }

    public boolean isAiring() {
        return airing;
    }

    public void setAiring(boolean airing) {
        this.airing = airing;
    }

    public int getEpisodeDurationInMinutes() {
        return episodeDurationInMinutes;
    }

    public int getTotalVisualizacoes() {
        return totalVisualizacoes;
    }

    public void setTotalVisualizacoes(int totalVisualizacoes) {
        this.totalVisualizacoes = totalVisualizacoes;
    }

    public void setEpisodeDurationInMinutes(int episodeDurationInMinutes) {
        this.episodeDurationInMinutes = episodeDurationInMinutes;
    }

    @Override
    public int getRating(){
        if(this.totalVisualizacoes>100){
            return 4;
        }
        return 2;
    }
}
