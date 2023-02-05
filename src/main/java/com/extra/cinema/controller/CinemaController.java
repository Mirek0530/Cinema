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

    @GetMapping(value = "movie={id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable int id) throws MovieNotFoundException {
        return ResponseEntity.ok(mapper.mapMovieToMovieDto(service.getMovie(id)));
    }

    @PutMapping(value = "movies")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        Movie movie = mapper.mapMovieDtoToMovie(movieDto);
        Movie updatedMovie = service.createMovie(movie);
        return ResponseEntity.ok(mapper.mapMovieToMovieDto(updatedMovie));
    }

    @DeleteMapping(value = "movie={id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
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

    @PutMapping(value = "rooms")
    public ResponseEntity<RoomDto> updateRoom(@RequestBody RoomDto roomDto) {
        Room room = mapper.mapRoomDtoToRoom(roomDto);
        Room updatedRoom = service.createRoom(room);
        return ResponseEntity.ok(mapper.mapRoomToRoomDto(updatedRoom));
    }

    @DeleteMapping(value = "room={id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        service.deleteRoom(id);
        return ResponseEntity.noContent().build();
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

    @PutMapping(value = "shows")
    public ResponseEntity<ShowDto> updateShow(@RequestBody ShowDto showDto) throws ShowCannotBeCreatedException {
        Show show = mapper.mapShowDtoToShow(showDto);
        Show updatedShow = service.createShow(show);
        return ResponseEntity.ok(mapper.mapShowToShowDto(updatedShow));
    }

    @DeleteMapping(value = "show={id}")
    public ResponseEntity<Void> deleteShow(@PathVariable int id) {
        service.deleteShow(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "createticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticketDto) throws TicketCannotBeCreatedException {
        Ticket ticket = mapper.mapTicketDtoToTicket(ticketDto);
        return ResponseEntity.ok(service.createTicket(ticket));
    }

    @GetMapping(value = "getalltickets")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<Ticket> tickets = service.getAllTickets();
        return ResponseEntity.ok(mapper.mapToTicketDtoList(tickets));
    }

    @GetMapping(value = "ticket={id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable int id) throws TicketNotFoundException {
        return ResponseEntity.ok(mapper.mapTicketToTicketDto(service.getTicket(id)));
    }

    @PutMapping(value = "tickets")
    public ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto) throws TicketCannotBeCreatedException {
        Ticket ticket = mapper.mapTicketDtoToTicket(ticketDto);
        Ticket updatedTicket = service.createTicket(ticket);
        return ResponseEntity.ok(mapper.mapTicketToTicketDto(updatedTicket));
    }

    @DeleteMapping(value = "ticket={id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
