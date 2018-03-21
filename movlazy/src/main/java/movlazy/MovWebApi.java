/*
 * Copyright (c) 2018 Miguel Gamboa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package movlazy;

import movlazy.dto.CastItemDto;
import movlazy.dto.MovieDto;
import movlazy.dto.PersonDto;
import movlazy.dto.SearchItemDto;
import util.IRequest;

/**
 * @author Miguel Gamboa
 *         created on 16-02-2017
 */

public class MovWebApi {
    /*
     * Constants
     */
    private static final String MOVIE_DB_HOST = "https://api.themoviedb.org/3/";
    private static final String MOVIE_DB_SEARH = "search/movie?api_key=%s&query=%s&page=%s";
    private static final String MOVIE_DB_MOVIE = "movie/%s?api_key=%s";
    private static final String MOVIE_DB_MOVIE_CREDITS = "movie/%s/credits?api_key=%s";
    private static final String MOVIE_DB_PERSON = "person/%s?api_key=%s";
    private static final String MOVIE_DB_PERSON_CREDITS = "person/%s/movie_credits?api_key=%s";
    private static final String API_KEY = "************************";

    /*
     * Constructors
     */
    public MovWebApi(IRequest req) {

    }

    /**
     * E.g. https://api.themoviedb.org/3/search/movie?api_key=***************&query=war+games
     */
    public SearchItemDto[] search(String title, int page) {
        throw new UnsupportedOperationException();
    }

    /**
     * E.g. https://api.themoviedb.org/3/movie/860?api_key=***************
     */
    public MovieDto getMovie(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     * E.g. https://api.themoviedb.org/3/movie/860/credits?api_key=***************
     */
    public CastItemDto[] getMovieCast(int movieId) {
        throw new UnsupportedOperationException();
    }

    /**
     * E.g. https://api.themoviedb.org/3/person/4756?api_key=***************
     */
    public PersonDto getPerson(int personId) {
        throw new UnsupportedOperationException();
    }

    /**
     * E.g. https://api.themoviedb.org/3/person/4756/movie_credits?api_key=***************
     */
    public SearchItemDto[] getPersonCreditsCast(int personId) {
        throw new UnsupportedOperationException();
    }
}