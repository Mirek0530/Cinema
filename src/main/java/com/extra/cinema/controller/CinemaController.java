package com.extra.cinema.controller;

import com.extra.cinema.Movie;
import com.extra.cinema.Room;
import com.extra.cinema.Show;
import com.extra.cinema.Ticket;
import com.extra.cinema.dto.MovieDto;
import com.extra.cinema.dto.RoomDto;
import com.extra.cinema.dto.ShowDto;
import com.extra.cinema.dto.TicketDto;
import com.extra.cinema.exception.*;
import com.extra.cinema.mapper.Mapper;
import com.extra.cinema.service.CinemaDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaDbService service;
    private final Mapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createmovie")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDto movieDto) {
        Movie movie = mapper.mapMovieDtoToMovie(movieDto);
        return ResponseEntity.ok(service.createMovie(movie));
    }

    @GetMapping(value = "getallmovies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<Movie> movies = service.getAllMovies();
        return ResponseEntity.ok(mapper.mapToMovieDtoList(movies));
    }

    @PostMapping(value = "createroom")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto) {
        Room room = mapper.mapRoomDtoToRoom(roomDto);
        return ResponseEntity.ok(service.createRoom(room));
    }

    @GetMapping(value = "getallrooms")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<Room> rooms = service.getAllRooms();
        return ResponseEntity.ok(mapper.mapToRoomDtoList(rooms));
    }

    @GetMapping(value = "room={id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable int id) throws RoomNotFoundException {
        return ResponseEntity.ok(mapper.mapRoomToRoomDto(service.getRoom(id)));
    }

    @GetMapping(value = "movie={id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable int id) throws MovieNotFoundException {
        return ResponseEntity.ok(mapper.mapMovieToMovieDto(service.getMovie(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createshow")
    public ResponseEntity<Show> createShow(@RequestBody ShowDto showDto) throws ShowCannotBeCreatedException {
        Show show = mapper.mapShowDtoToShow(showDto);
        return ResponseEntity.ok(service.createShow(show));
    }

    @GetMapping(value = "getallshows")
    public ResponseEntity<List<ShowDto>> getAllShows() {
        List<Show> shows = service.getAllShows();
        return ResponseEntity.ok(mapper.mapToShowDtoList(shows));
    }

    @GetMapping(value = "show={id}")
    public ResponseEntity<ShowDto> getShow(@PathVariable int id) throws ShowNotFoundException {
        return ResponseEntity.ok(mapper.mapShowToShowDto(service.getShow(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticketDto) throws TicketCannotBeCreatedException {
        Ticket ticket = mapper.mapTicketDtoToTicket(ticketDto);
        return ResponseEntity.ok(service.addTicket(ticket));
    }

    @GetMapping(value = "getalltickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<Ticket> tickets = service.getAllTickets();
        return ResponseEntity.ok(mapper.mapToTicketDtoList(tickets));
    }

}
