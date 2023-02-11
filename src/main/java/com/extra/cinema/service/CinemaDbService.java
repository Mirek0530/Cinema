package com.extra.cinema.service;

import com.extra.cinema.entity.Movie;
import com.extra.cinema.entity.Room;
import com.extra.cinema.entity.Show;
import com.extra.cinema.entity.Ticket;
import com.extra.cinema.exception.*;
import com.extra.cinema.repository.MovieRepository;
import com.extra.cinema.repository.RoomRepository;
import com.extra.cinema.repository.ShowRepository;
import com.extra.cinema.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaDbService {

    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public Movie createMovie(final Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(int id) throws MovieNotFoundException {
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public Room createRoom(final Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(int id) throws RoomNotFoundException {
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public Show createShow(final Show show) throws ShowCannotBeCreatedException {
        if (movieRepository.findById(show.getMovie().getId()).isPresent()) {

            Movie movie = movieRepository.findById(show.getMovie().getId()).get();

            Show showToSave = new Show(
                    show.getId(),
                    movie,
                    show.getStartTime(),
                    show.getTickets()
            );

            return showRepository.save(showToSave);
        } else {
            throw new ShowCannotBeCreatedException();
        }
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShow(int id) throws ShowNotFoundException {
        return showRepository.findById(id).orElseThrow(ShowNotFoundException::new);
    }

    public void deleteShow(int id) {
        showRepository.deleteById(id);
    }

    public Ticket createTicket(Ticket ticket) throws TicketCannotBeCreatedException {
        if (showRepository.findById(ticket.getShowId()).isPresent()) {
            return ticketRepository.save(ticket);
        } else {
            throw new TicketCannotBeCreatedException();
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(int id) throws TicketNotFoundException {
        return ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}
