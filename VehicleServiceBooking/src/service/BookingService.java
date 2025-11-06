package service;

import java.io.*;
import java.util.*;
import model.Booking;

public class BookingService {
    private static final String BOOKING_FILE = "data/bookings.txt";

    public void createBooking(Booking booking) {
        try (FileWriter fw = new FileWriter(BOOKING_FILE, true)) {
            fw.write(booking.toString() + "\n");
            System.out.println("✅ Booking created successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving booking.");
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Booking booking = Booking.fromString(line);
                if (booking != null) list.add(booking);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading bookings.");
        }
        return list;
    }

    public void updateStatus(String userEmail, String vehicleNo, String newStatus) {
        List<Booking> list = getAllBookings();
        try (FileWriter fw = new FileWriter(BOOKING_FILE)) {
            for (Booking b : list) {
                if (b.getUserEmail().equals(userEmail) && b.getVehicleNo().equals(vehicleNo)) {
                    b.setStatus(newStatus);
                }
                fw.write(b.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error updating status.");
        }
    }
}
