package com.extra.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieDto {
    private int id;
    private String title;
    private int duration;
}
