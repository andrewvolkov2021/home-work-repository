package by.it_academy.jd2.Mk_JD2_82_21_vote.service;

import by.it_academy.jd2.Mk_JD2_82_21_vote.controller.AboutException;
import by.it_academy.jd2.Mk_JD2_82_21_vote.controller.GenresException;
import by.it_academy.jd2.Mk_JD2_82_21_vote.storage.VoteStorage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class VoteService {

    private final static VoteService instance = new VoteService();
    private final VoteStorage storage;

    private VoteService() {
        this.storage = VoteStorage.getInstance();
    }

    public void addVote(String artist, String[] genres, String about) throws GenresException, AboutException {
        try {
            if (genres.length < 3 || genres.length > 5) {
                throw new GenresException();
            }
            if (about == "") {
                throw new AboutException();
            }

        } catch(NullPointerException nullEx) {
            throw new GenresException();
        }

        this.storage.getAbout().add(about);
        Integer artistPosition = this.storage.getArtist().getOrDefault(artist, 0);
        this.storage.getArtist().put(artist, ++artistPosition);

        if(genres != null) {
            for (String genre : genres) {
                Integer genrePosition = this.storage.getGenre().getOrDefault(genre, 0);
                this.storage.getGenre().put(genre, ++genrePosition);
            }
        }
    }

    public Map<String, Integer> getArtistResult(){
        return this.storage.getArtist();
    }

    public Map<String, Integer> getGenreResult(){
        return this.storage.getGenre();
    }

    public List<String> getAboutResult(){
        return this.storage.getAbout();
    }

    public static VoteService getInstance() {
        return instance;
    }
}
