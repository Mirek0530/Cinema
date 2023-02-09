package com.extra.cinema;

import com.extra.cinema.controller.CinemaController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CinemaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CinemaController cinemaController;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void testRoomCreate() throws Exception {
        //Given
        Room room = new Room(1, 250, new ArrayList<>());

        //When
        ResultActions response = mockMvc.perform(post("/cinema/createroom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)));

        //Then
        response.andDo(print())
                .andExpect(jsonPath("$.id", is(room.getId())))
                .andExpect(jsonPath("$.seats", is(room.getSeats())));

        //CleanUp
        cinemaController.deleteRoom(room.getId());
    }


}
