package pfe.com.mrcore.core.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateCalculator {

    public Integer getAge(Date birthdayDate) {

        LocalDate birthdayLocalDate = getLocalDate(birthdayDate);
        LocalDate today = LocalDate.now();

        return Period.between(birthdayLocalDate, today).getYears();
    }

    public long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {

        long diff = date2.getTime() - date1.getTime();

        return timeUnit.convert(diff,TimeUnit.MILLISECONDS);
    }

    private LocalDate getLocalDate(Date date) {

       return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
