package org.example.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator {

    public static boolean isValidDate(String dateString) {
        // Парсим строку с датой в формат LocalDate
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, formatter);

            // Проверяем, что дата соответствует календарю
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();

            if (year < 0 || month < 1 || month > 12) {
                return false;
            }

            // Проверяем количество дней в месяце
            if (day < 1 || day > date.lengthOfMonth()) {
                return false;
            }

            // Проверяем на високосный год
            if (month == 2 && day == 29 && !date.isLeapYear()) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
