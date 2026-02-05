package com.harshchoudhary.projects.AirBnb_SpringBoot.service.bookingsAndPayments;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Bookings.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
