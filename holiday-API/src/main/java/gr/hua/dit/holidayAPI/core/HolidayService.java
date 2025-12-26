package gr.hua.dit.holidayAPI.core;

import java.time.LocalDate;

public interface HolidayService {

    boolean isHoliday(LocalDate date);
}