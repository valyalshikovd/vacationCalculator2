package com.example.vacationCalculator.servive;

public interface VacationCalculatorService {

    double calculateSalary(
            Double averageSalary,
            Integer amountDay);
    double calculateSalary(
            Double averageSalary,
            Integer amountDay,
            String date);
}
