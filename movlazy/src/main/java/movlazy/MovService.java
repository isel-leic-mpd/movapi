/*
 * Copyright (c) 2017, Miguel Gamboa
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
import movlazy.dto.SearchItemDto;
import movlazy.model.Actor;
import movlazy.model.CastItem;
import movlazy.model.SearchItem;
import movlazy.model.Movie;
import util.IRequest;
import util.Queries;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static util.Queries.*;
import static util.Queries.flatMap;
import static util.Queries.iterate;
import static util.Queries.map;
import static util.Queries.takeWhile;

/**
 * @author Miguel Gamboa
 *         created on 02-03-2017
 */
public class MovService {

    private final MovWebApi movWebApi;
    private final Map<Integer, Movie> movies = new HashMap<>();
    private final Map<Integer, List<CastItem>> cast = new HashMap<>();
    private final Map<Integer, Actor> actors = new HashMap<>();

    public MovService(MovWebApi movWebApi) {
        this.movWebApi = movWebApi;
    }

    public MovService(IRequest req) {
        this.movWebApi = new MovWebApi(req);
    }

    public Iterable<SearchItem> search(String name) {
        return
        map(                     // Iterable<SearchItem>
            flatMap(             // Iterable<SearchItemDto>
                takeWhile(       // Iterable<SearchItemDto[]>
                    map(         // Iterable<SearchItemDto[]>
                        iterate( // Iterable<Integer>
                                0,
                                prev -> ++prev),
                        page -> movWebApi.search(name, page)),
                    movs -> movs.length != 0),
                movs -> of(movs)),
            this::searchItemFromDto);
    }

    private SearchItem searchItemFromDto(SearchItemDto dto) {
        return new SearchItem(
                dto.getId(),
                dto.getTitle(),
                dto.getReleaseDate(),
                dto.getVoteAverage(),
                () -> getMovie(dto.getId()));
    }

    public Movie getMovie(int movId) {
        return movies.computeIfAbsent(movId, id -> {
            MovieDto mov = movWebApi.getMovie(id);
            return new Movie(
                    mov.getId(),
                    mov.getOriginalTitle(),
                    mov.getTagline(),
                    mov.getOverview(),
                    mov.getVoteAverage(),
                    mov.getReleaseDate(),
                    () -> this.getMovieCast(id));
        });
    }

    public List<CastItem> getMovieCast(int movId) {
        return cast.computeIfAbsent(movId, id -> {
            return Collections.EMPTY_LIST;
        });
    }


    private CastItem castFromDto(
            int movieId,
            CastItemDto dto) {
        throw new UnsupportedOperationException();
    }

    public Actor getActor(int actorId, String name) {
        throw new UnsupportedOperationException();
    }

    public Iterable<SearchItem> getActorCreditsCast(int actorId) {
        return Queries.empty();
    }

}
