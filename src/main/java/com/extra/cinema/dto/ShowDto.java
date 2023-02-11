package com.extra.cinema.dto;

import com.extra.cinema.entity.Movie;
import com.extra.cinema.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ShowDto {
    private int id;
    private Movie movie;
    private LocalDateTime startTime;
    private List<Ticket> tickets;
}
