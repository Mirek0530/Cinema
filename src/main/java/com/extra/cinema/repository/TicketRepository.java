package com.extra.cinema.repository;

import com.extra.cinema.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    List<Ticket> findAll();

    void deleteById(Integer id);
}
