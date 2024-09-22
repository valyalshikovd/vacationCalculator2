package com.example.vacationCalculator.servive.impl;

import com.example.vacationCalculator.servive.DateParseService;
import com.example.vacationCalculator.servive.DayService;
import com.example.vacationCalculator.servive.VacationCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private final DayService dayService;

    private final DateParseService dateParseService;

    @Override
    public double calculateSalary(Double averageSalary, Integer amountDay) {
        return getSalary(amountDay, averageSalary);
    }

    @Override
    public double calculateSalary(Double averageSalary, Integer amountDay, String date) {
        return getSalary(dayService.getAmountWorkDay(dateParseService.parseDate(date), amountDay), averageSalary);
    }

    /**
     * Метод, расчитывающий зарплату
     * @param countDays - число рабочих дней
     * @param averageSalary - средняя зарплата в месяц
     */
    private Double getSalary(int countDays, Double averageSalary) {
        if(countDays <= 0) {
            throw new IllegalArgumentException("Некорректное число дней для расчета");
        }

        if(averageSalary == null || averageSalary <= 0) {
            throw new IllegalArgumentException("Некорректная зарплата");
        }
        return averageSalary * (countDays / 30.0);
    }

}
