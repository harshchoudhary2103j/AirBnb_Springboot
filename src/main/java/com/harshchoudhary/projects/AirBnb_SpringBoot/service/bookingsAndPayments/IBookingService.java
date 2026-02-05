package com.harshchoudhary.projects.AirBnb_SpringBoot.service.bookingsAndPayments;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Booking.BookingDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Booking.BookingRequestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.User.GuestDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.Hotel.HotelReportDTO;
import com.stripe.model.Event;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
   BookingDTO initialiseBooking(BookingRequestDTO bookingRequestDTO);

   BookingDTO addGuest(List<GuestDTO> guestDTOList, Long bookingId);

   void deleteAllBookingsByRoom(Long roomId);

    String initiatePayment(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);


     List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    HotelReportDTO getReportByHotelId(Long hotelId, LocalDate startDate, LocalDate endDate);

    @Nullable List<BookingDTO> getMyBookings();
}
