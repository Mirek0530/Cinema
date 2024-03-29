package com.extra.cinema.dto;

import com.extra.cinema.entity.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class RoomDto {
    private int id;
    private int seats;
    private List<Show> shows;
}
