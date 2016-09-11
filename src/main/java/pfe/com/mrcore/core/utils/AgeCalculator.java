package pfe.com.mrcore.core.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class AgeCalculator {

    public Integer getAge(Date birthdayDate) {

        LocalDate birthdayLocalDate = getLocalDate(birthdayDate);
        LocalDate today = LocalDate.now();

        return Period.between(birthdayLocalDate, today).getYears();
    }

    private LocalDate getLocalDate(Date date) {

       return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
