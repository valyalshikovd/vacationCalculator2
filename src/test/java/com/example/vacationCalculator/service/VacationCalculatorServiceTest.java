package com.example.vacationCalculator.service;

import com.example.vacationCalculator.servive.DayService;
import com.example.vacationCalculator.servive.VacationCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VacationCalculatorServiceTest {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @MockBean
    private DayService dayService;

    @Test
    public void calculateTest(){
        assertEquals(500.0, vacationCalculatorService.calculateSalary(1000.0, 15));
    }


    @Test
    public void calculateWithDateTest(){
        Mockito
                .when(
                        dayService.getAmountWorkDay
                                (
                                        LocalDate.of(
                                                2024,
                                                9,
                                                20),
                                        20
                                )
                ).thenReturn(15);
        assertEquals(500.0, vacationCalculatorService.calculateSalary(1000.0, 20, "2024-09-20" ));
    }
}
