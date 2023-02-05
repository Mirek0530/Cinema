package com.extra.cinema.repository;

import com.extra.cinema.Show;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface ShowRepository extends CrudRepository<Show, Integer> {

    Show save(Show show);

    List<Show> findAll();

    void deleteById(Integer id);
}
