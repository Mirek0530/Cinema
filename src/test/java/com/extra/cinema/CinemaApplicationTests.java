package com.extra.cinema;

import com.extra.cinema.dto.RoomDto;
import com.extra.cinema.entity.Room;
import com.extra.cinema.repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CinemaApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Nested
    class testRoomOperations {
        @Test
        void testRoomCreate() throws Exception {
            //Given
            Room room = new Room(1, 203, new ArrayList<>());

            //When
            ResultActions response = mockMvc.perform(post("/cinema/createroom")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(room)));

            //Then
            response.andDo(print())
                    .andExpect(jsonPath("$.seats", is(room.getSeats())));

            //CleanUp
            roomRepository.deleteAll();
        }

        @Test
        void testRoomGetWithCorrectId() throws Exception {
            //Given
            Room room = new Room(1, 252, new ArrayList<>());

            //When
            Room savedRoom = roomRepository.save(room);

            ResultActions response = mockMvc.perform(get("/cinema/room=" + savedRoom.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(savedRoom)));

            //Then
            response.andDo(print())
                    .andExpect(jsonPath("$.seats", is(savedRoom.getSeats())));

            //CleanUp
            roomRepository.deleteAll();
        }

        @Test
        void testRoomGetWithWrongId() throws Exception {
            //Given
            Room room = new Room(1, 252, new ArrayList<>());

            //When
            Room savedRoom = roomRepository.save(room);

            ResultActions response = mockMvc.perform(get("/cinema/room=" + savedRoom.getId() + 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(savedRoom)));

            //Then
            response.andExpect(status().isBadRequest());

            //CleanUp
            roomRepository.deleteAll();
        }

        @Test
        void testRoomUpdate() throws Exception {
            //Given
            Room room = new Room(1, 250, new ArrayList<>());

            //When
            Room savedRoom = roomRepository.save(room);
            Room room2 = new Room(savedRoom.getId(), 300, new ArrayList<>());

            ResultActions response = mockMvc.perform(put("/cinema/rooms")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(room2)));

            //Then
            response.andDo(print())
                    .andExpect(jsonPath("$.seats", is(room2.getSeats())));

            //CleanUp
            roomRepository.deleteAll();
        }

        @Test
        void testRoomDelete() throws Exception {
            //Given
            Room room = new Room(1, 250, new ArrayList<>());

            //When
            Room savedRoom = roomRepository.save(room);

            mockMvc.perform(delete("/cinema/room=" + savedRoom.getId()));

            //Then
            assertEquals(0, roomRepository.findAll().size());

            //CleanUp
        }
    }


}
