package com.example.vacationCalculator.controller;

import com.example.vacationCalculator.servive.VacationCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationController.class)
public class VacationControllerTest {

    @MockBean
    private VacationCalculatorService vacationCalculatorService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers()  {

        try {
            Mockito.when(
                            vacationCalculatorService.calculateSalary(
                                    1000.0,
                                    20,
                                    "2024-09-20")
                    )
                    .thenReturn(500.0);

            mockMvc.perform(
                            get("/api/v1/calculate")
                                    .param("averageSalary", "1000")
                                    .param("amountDay", "20")
                                    .param("date", "2024-09-20"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("500.0"));
        }catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetUsersIncorrectData(){
        try {
            Mockito.when(
                            vacationCalculatorService.calculateSalary(
                                    1000.0,
                                    20,
                                    "2024-9-20")
                    )
                    .thenThrow(new IllegalArgumentException());

            mockMvc.perform(
                            get("/api/v1/calculate")
                                    .param("averageSalary", "1000")
                                    .param("amountDay", "20")
                                    .param("date", "2024-9-20"))
                    .andExpect(status().isUnprocessableEntity());
        }catch (Exception e) {
            fail();
        }
    }
}
