package com.extra.cinema.controller;

import com.extra.cinema.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException exception) {
        return new ResponseEntity<>("Room with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException exception) {
        return new ResponseEntity<>("Movie with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowCannotBeCreatedException.class)
    public ResponseEntity<Object> handleShowCannotBeCreatedException(ShowCannotBeCreatedException exception) {
        return new ResponseEntity<>("Show cannot be created (e.g. wrong movie id)", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketCannotBeCreatedException.class)
    public ResponseEntity<Object> handleTicketCannotBeCreatedException(TicketCannotBeCreatedException exception) {
        return new ResponseEntity<>("Ticket cannot be created, show with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<Object> handleShowNotFoundException(ShowNotFoundException exception) {
        return new ResponseEntity<>("Show with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Object> handleTicketNotFoundException(TicketNotFoundException exception) {
        return new ResponseEntity<>("Ticket with given id does not exist", HttpStatus.BAD_REQUEST);
    }
}
