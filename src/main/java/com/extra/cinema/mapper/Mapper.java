package com.extra.cinema.mapper;

import com.extra.cinema.entity.Movie;
import com.extra.cinema.entity.Room;
import com.extra.cinema.entity.Show;
import com.extra.cinema.entity.Ticket;
import com.extra.cinema.dto.MovieDto;
import com.extra.cinema.dto.RoomDto;
import com.extra.cinema.dto.ShowDto;
import com.extra.cinema.dto.TicketDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public Movie mapMovieDtoToMovie(MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getDuration()
        );
    }

    public MovieDto mapMovieToMovieDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration()
        );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movies) {
        return movies.stream()
                .map(this::mapMovieToMovieDto)
                .collect(Collectors.toList());
    }

    public Room mapRoomDtoToRoom(RoomDto roomDto) {
        return new Room(
                roomDto.getId(),
                roomDto.getSeats(),
                roomDto.getShows()
        );
    }

    public RoomDto mapRoomToRoomDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getSeats(),
                room.getShows()
        );
    }

    public List<RoomDto> mapToRoomDtoList(List<Room> rooms) {
        return rooms.stream()
                .map(this::mapRoomToRoomDto)
                .collect(Collectors.toList());
    }

    public Show mapShowDtoToShow(ShowDto showDto) {
        return new Show(
                showDto.getId(),
                showDto.getMovie(),
                showDto.getStartTime(),
                showDto.getTickets()
        );
    }

    public ShowDto mapShowToShowDto(Show show) {
        return new ShowDto(
                show.getId(),
                show.getMovie(),
                show.getStartTime(),
                show.getTickets()
        );
    }

    public List<ShowDto> mapToShowDtoList(List<Show> shows) {
        return shows.stream()
                .map(this::mapShowToShowDto)
                .collect(Collectors.toList());
    }

    public Ticket mapTicketDtoToTicket(TicketDto ticketDto) {
        return new Ticket(
                ticketDto.getId(),
                ticketDto.getSeat(),
                ticketDto.getPrice(),
                ticketDto.getShowId()
        );
    }

    public TicketDto mapTicketToTicketDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getSeat(),
                ticket.getPrice(),
                ticket.getShowId()
        );
    }

    public List<TicketDto> mapToTicketDtoList(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::mapTicketToTicketDto)
                .collect(Collectors.toList());
    }
}


