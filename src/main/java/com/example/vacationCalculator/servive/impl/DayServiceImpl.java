package com.example.vacationCalculator.servive.impl;

import com.example.vacationCalculator.dto.HolidayDto;
import com.example.vacationCalculator.servive.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DayService {

    private final static List<DayOfWeek> weekend = Arrays.asList(new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY});

    private final HashMap<Integer, List<HolidayDto>> holidays = new HashMap<>();

    private final RestTemplate restTemplate;

    /**
     * метод расчитывающий количество рабочих дней с учетом праздников и выходных дней
     * @param date - последний рабочий день, перед отпуском
     * @param amountDay - общее число дней отпуска, включая выходные и праздники
     * @return
     */
    @Override
    public int getAmountWorkDay(LocalDate date, int amountDay) {

        int year = date.getYear();
        addHolidays(year);
        LocalDate endVacation = date.plusDays(amountDay).plusDays(1);
        int diffYears = endVacation.getYear() - year;
        ArrayList<Integer> years = new ArrayList<>();
        years.add(year);
        for (int i = 0; i < diffYears; i++) {
            years.add(year+1);
        }
        for(Integer i : years) {
            if(this.holidays.get(i) == null) {
                addHolidays(i);
            }
        }
        LocalDate currentDate = date;
        int countDays = 0;
        while (currentDate.isBefore(endVacation)) {
            currentDate = currentDate.plusDays(1);
            LocalDate finalCurrentDate = currentDate;
            if(this.holidays
                    .get(currentDate.getYear())
                    .stream()
                    .anyMatch(dateDto -> dateDto.getDate().equals(finalCurrentDate))) {
                continue;
            }
            if (weekend.contains(currentDate.getDayOfWeek())) {
                continue;
            }
            countDays++;
        }
        return countDays;
    }

    /**
     * Метод, добавляющий праздничные даты в общую коллекцию из стороннего источника
     * @param year - год, за который нужн подобрать праздничные даты
     */
    private void addHolidays(int year) {
        ResponseEntity<HolidayDto[]> holidays = restTemplate.getForEntity("https://date.nager.at/api/v3/publicholidays/" + year+"/RU", HolidayDto[].class);
        if(holidays.getBody() != null) {
            this.holidays.put(year, List.of(holidays.getBody()));
        }
    }
}
