package com.extra.cinema.dto;

import com.extra.cinema.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TicketDto {
    private int id;
    private int seat;
    private BigDecimal price;
    private int showId;
}
