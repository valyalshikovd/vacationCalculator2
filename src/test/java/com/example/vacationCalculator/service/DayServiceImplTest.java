package com.example.vacationCalculator.service;

import com.example.vacationCalculator.dto.HolidayDto;
import com.example.vacationCalculator.servive.DayService;
import com.example.vacationCalculator.servive.impl.DayServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

import java.time.LocalDate;
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DayServiceImplTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private DayService dayService;




    @Test
    public void simpleDayServiceTest() {
        Mockito
                .when(restTemplate
                        .getForEntity("https://date.nager.at/api/v3/publicholidays/" + 2024+"/RU", HolidayDto[].class))
                .thenReturn(new ResponseEntity<HolidayDto[]>(new HolidayDto[]{
                        new HolidayDto(
                                LocalDate.of(2024, 11, 4),
                                "День народного единства",
                                "The nation unity's day",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2024, 12, 31),
                                "Новый год",
                                "The new year",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        )
                }, HttpStatus.OK));
        Assertions.assertEquals(15, dayService.getAmountWorkDay(LocalDate.of(2024,9, 20), 20));
    }

    @Test
    public void dayServiceTest() {

        Mockito
                .when(restTemplate
                        .getForEntity("https://date.nager.at/api/v3/publicholidays/" + 2024+"/RU", HolidayDto[].class))
                .thenReturn(new ResponseEntity<HolidayDto[]>(new HolidayDto[]{
                        new HolidayDto(
                                LocalDate.of(2024, 11, 4),
                                "День народного единства",
                                "The nation unity's day",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2024, 12, 31),
                                "Новый год",
                                "The new year",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        )
                }, HttpStatus.OK));

        Mockito
                .when(restTemplate
                        .getForEntity("https://date.nager.at/api/v3/publicholidays/" + 2025+"/RU", HolidayDto[].class))
                .thenReturn(new ResponseEntity<HolidayDto[]>(new HolidayDto[]{
                        new HolidayDto(
                                LocalDate.of(2025, 1, 1),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2025, 1, 2),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2025, 1, 3),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2025, 1, 4),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2025, 1, 5),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                        new HolidayDto(
                                LocalDate.of(2025, 1, 6),
                                "Новогодние праздники",
                                "New years holidays",
                                null,
                                true,
                                true,
                                null,
                                1991,
                                null
                        ),
                }, HttpStatus.OK));
        Assertions.assertEquals(79, dayService.getAmountWorkDay(LocalDate.of(2024,9, 20), 120));
    }
}
