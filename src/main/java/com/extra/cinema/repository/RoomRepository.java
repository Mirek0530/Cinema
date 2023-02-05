package com.extra.cinema.repository;

import com.extra.cinema.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    Room save(Room room);

    List<Room> findAll();

    void deleteById(Integer id);
}
