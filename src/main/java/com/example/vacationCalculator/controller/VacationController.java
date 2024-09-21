package com.example.vacationCalculator.controller;


import com.example.vacationCalculator.servive.VacationCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VacationController {

    private final VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(
            @RequestParam Double averageSalary,
            @RequestParam Integer amountDay,
            @RequestParam(required = false) String date
    ){
        try {
            if(date == null){
                return ResponseEntity.ok(vacationCalculatorService.calculateSalary(averageSalary, amountDay));
            }

            return ResponseEntity.ok(vacationCalculatorService.calculateSalary(averageSalary, amountDay, date));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
