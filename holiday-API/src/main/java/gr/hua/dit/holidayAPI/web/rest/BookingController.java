package gr.hua.dit.holidayAPI.web.rest;

import gr.hua.dit.holidayAPI.core.HolidayService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final HolidayService holidayService;

    public BookingController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @PostMapping
    public String createBooking(@RequestParam String date) {

        LocalDate bookingDate = LocalDate.parse(date);

        if (holidayService.isHoliday(bookingDate)) {
            return "❌ Η βιβλιοθήκη είναι κλειστή (εθνική αργία)";
        }

        // εδώ θα μπει η αποθήκευση κράτησης
        return "✅ Η κράτηση ολοκληρώθηκε επιτυχώς";
    }
}