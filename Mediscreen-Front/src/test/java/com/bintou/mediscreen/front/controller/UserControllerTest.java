package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.User;
import com.bintou.mediscreen.front.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Alex", authorities = {"ADMIN"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeAll
    public void init() {
        User user = new User();
        user.setUsername("Alexandra25");
        user.setFullname("Boulanger");
        user.setPassword("password*23");
        user.setRole("USER");
        userService.save(user);
        for (User user1 : userService.findAll()) {
            if (user1.getUsername().equals("Alexandra25")) {
                id = user1.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteUser() {
        User user = userService.findByUsername("Alexandra25");
        userService.delete(user);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addUser() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateUser() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .param("username", "Alexandra25")
                        .param("fullname", "Boulanger")
                        .param("password", "password*23")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/user/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(post("/user/update/{id}", id)
                        .param("username", "John653")
                        .param("fullname", "John")
                        .param("password", "Password$John23")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/user/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }
}
