package com.demo.nisum.controller;

import com.demo.nisum.dto.LoginDto;
import com.demo.nisum.dto.PhoneDto;
import com.demo.nisum.dto.UserDetailDto;
import com.demo.nisum.dto.UserDto;
import com.demo.nisum.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc()
public class UserControllerTest {

    public static final String EMAIL = "cisco@gmail.com";
    public static final String VALID_PASSWORD = "A123456";
    public static final String NAME = "Francisco";
    public static final String SIGNUP_PATH = "/users/signup";
    public static final String URI_SIGNIN = "/users/signin";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXNjb0BnbWFpbC5jb20iLCJpYXQiOjE2NzkzMzMyMjAsImV4cCI6MTY3OTMzMzUyMH0.P4KUsS56aMbS2eB4IwxLhav9JUJ_DwTHqOeEZDNRINI";
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
        userDto.setEmail(EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(NAME);

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(UUID.randomUUID().toString());
        userDetailDto.setCreated(LocalDateTime.now());
        userDetailDto.setIsActive(true);
        userDetailDto.setName(NAME);


        Mockito.when(userService.create(userDto)).thenReturn( Optional.of(userDetailDto));

        mockMvc.perform(MockMvcRequestBuilders.post(SIGNUP_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("campo phones  El nodo teléfono no puede estar vacio"));
    }

    @Test
    public void signup_givenValidRequest_shouldReturnValidResponse() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail(EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(NAME);
        userDto.setPhones(Arrays.asList(
                new PhoneDto("NY","123","123")
        ));

        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(UUID.randomUUID().toString());
        userDetailDto.setCreated(LocalDateTime.now());
        userDetailDto.setIsActive(true);
        userDetailDto.setName(NAME);


        Mockito.when(userService.create(userDto)).thenReturn( Optional.of(userDetailDto));
        mockMvc.perform(MockMvcRequestBuilders.post(SIGNUP_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void signin_givenWrongCredentails_shouldReturnBadRequest() throws Exception {
        LoginDto loginDto = LoginDto.builder().email(EMAIL).password(VALID_PASSWORD).build();
        Mockito.when(userService.signin(loginDto.getEmail(), loginDto.getPassword()))
                .thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders.post(URI_SIGNIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto))
                        .header(HEADER_AUTHORIZATION_KEY, JWT_TOKEN))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()
        );
    }
}
