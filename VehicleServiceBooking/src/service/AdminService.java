package service;

import model.Booking;
import java.util.*;

public class AdminService {
    private final String adminUser = "admin";
    private final String adminPass = "admin123";

    public boolean login(String username, String password) {
        return username.equals(adminUser) && password.equals(adminPass);
    }

    public void viewAllBookings(BookingService bookingService) {
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking b : bookings) {
                System.out.println("User: " + b.getUserEmail() + " | Vehicle: " + b.getVehicleNo()
                        + " | Service: " + b.getServiceName() + " | Date: " + b.getDate()
                        + " | Status: " + b.getStatus());
            }
        }
    }
}
