package com.demo.nisum.controller;

import com.demo.nisum.dto.PhoneDto;
import com.demo.nisum.dto.UserDetailDto;
import com.demo.nisum.dto.UserDto;
import com.demo.nisum.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc()
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init(){
        userService = Mockito.mock(UserService.class);
    }

    @Test
    public void given_invalidPayload_shouldReturnStatus400() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("cisco@gmail.com");
        userDto.setPassword("A123456");
        userDto.setName("Francisco");

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(UUID.randomUUID().toString());
        userDetailDto.setCreated(LocalDateTime.now());
        userDetailDto.setIsActive(true);
        userDetailDto.setName("Francisco");


        Mockito.when(userService.create(userDto)).thenReturn( Optional.of(userDetailDto));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("campo phones  El nodo teléfono no puede estar vacio"));
    }

    @Test
    public void given_validRequest_shouldReturnValidResponse() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("cisco@gmail.com");
        userDto.setPassword("A123456");
        userDto.setName("Francisco");
        userDto.setPhones(Arrays.asList(
                new PhoneDto("NY","123","123")
        ));

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(UUID.randomUUID().toString());
        userDetailDto.setCreated(LocalDateTime.now());
        userDetailDto.setIsActive(true);
        userDetailDto.setName("Francisco");


        Mockito.when(userService.create(userDto)).thenReturn( Optional.of(userDetailDto));
        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
