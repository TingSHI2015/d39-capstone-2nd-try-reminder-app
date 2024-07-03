package com.github.tingshi2015.backend.tips;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TipControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TipRepository tipRepository;

    @Test
    void getAllTips_shouldReturnEmptyList_whenRepoIsEmpty() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tips"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                                            []
                                                                           """));
    }

    @Test
    @DirtiesContext
    void getAllTips_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepoIsEmpty() throws Exception {
        //GIVEN
        Tip tip = new Tip("id100", "Drink Water!");
        tipRepository.save(tip);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tips"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                  [{
                                    "id": "id100",
                                    "content": "Drink Water!"
                                  }]
                                  """));
    }

    @Test
    @DirtiesContext
    void addTip_shouldReturnCreatedTip() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                        {
                          "content": "Drink Water!"
                        }
                        """))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        {
                        "content": "Drink Water!"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void deleteTip() throws Exception {
        //GIVEN
        Tip existingTip = new Tip("id101", "Drink Beer");
        tipRepository.save(existingTip);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tips/id101"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DirtiesContext
    void putATip() throws Exception {
        //GIVEN
        Tip exsistedTip = new Tip("id99", "Hahaha");
        tipRepository.save(exsistedTip);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tips/id99")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "content": "Updated-Hahaha"
                        }
                        """))
        //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                         "id": "id99",
                         "content": "Updated-Hahaha"
                        }
        """));
}

}