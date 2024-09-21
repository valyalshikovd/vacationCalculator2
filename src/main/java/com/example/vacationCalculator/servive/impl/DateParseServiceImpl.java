package com.example.vacationCalculator.servive.impl;

import com.example.vacationCalculator.servive.DateParseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class DateParseServiceImpl implements DateParseService {

    @Override
    public LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        }catch (Exception e){
            throw new IllegalArgumentException("Неверный формат даты");
        }
    }
}
