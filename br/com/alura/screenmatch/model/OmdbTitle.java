package br.com.alura.screenmatch.model;

// Record para ter os nomes de campos do Json
public record OmdbTitle(String Title,
                        String Year,
                        String Rated,
                        String Released,
                        String Runtime,
                        String Genre,
                        String Director,
                        String Writer,
                        String Actors,
                        String Plot,
                        String Country,
                        String Metascore,
                        String imdbRating,
                        String imdbVotes,
                        String totalSeasons

){ }
