package com.group12.trek;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.group12.trek.controllers.PlaceController;
import com.group12.trek.models.PlaceService;
import com.group12.trek.models.PostService;
import com.group12.trek.models.UserService;
import com.group12.trek.models.VoteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlaceController.class)
public class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaceController placeController;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private PostService postService;

    @MockBean
    private VoteService voteService;

    @MockBean
    private UserService userService;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up");
    }

    @Test
    void testContextLoads() throws Exception {
        assertNotNull(placeController);
    }

    @Test
    void testListPlaces() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(model().attributeExists("places"));
    }

    @Test
    void testAddPlaces() throws Exception {
        mockMvc.perform(post("/addPlace")
            .param("geohash", "c2b86ft5x6vz")
            .param("name", "SFU")
            .param("description", " boring ass place"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));
    }
}
