package com.extra.cinema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOMS")
public class Room {

    private int id;
    private int seats;
    private List<Show> shows;

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "ROOM_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "SEATS")
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @OneToMany(
            targetEntity = Show.class,
            mappedBy = "id",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
