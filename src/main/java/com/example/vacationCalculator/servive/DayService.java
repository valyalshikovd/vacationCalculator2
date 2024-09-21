package com.example.vacationCalculator.servive;

import java.time.LocalDate;

public interface DayService {

    int getAmountWorkDay(LocalDate date, int amountDay);
}
