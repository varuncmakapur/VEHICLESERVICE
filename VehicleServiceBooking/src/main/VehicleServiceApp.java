package main;

import java.util.*;
import model.*;
import service.*;

public class VehicleServiceApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        AdminService adminService = new AdminService();

        while (true) {
            System.out.println("\n===== VEHICLE SERVICE BOOKING SYSTEM =====");
            System.out.println("1. User Register");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> registerUser(userService);
                case 2 -> userLogin(userService, bookingService);
                case 3 -> adminLogin(adminService, bookingService);
                case 4 -> {
                    System.out.println("👋 Exiting... Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerUser(UserService userService) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        userService.registerUser(new User(name, email, phone, password));
    }

    private static void userLogin(UserService userService, BookingService bookingService) {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = userService.login(email, password);
        if (user == null) {
            System.out.println("❌ Invalid login credentials.");
            return;
        }

        while (true) {
            System.out.println("\nWelcome, " + user.getName());
            System.out.println("1. Book Service");
            System.out.println("2. View My Bookings");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) {
                System.out.print("Enter Vehicle Number: ");
                String vehicleNo = sc.nextLine();
                System.out.print("Enter Service Type: ");
                String service = sc.nextLine();
                System.out.print("Enter Booking Date (dd-mm-yyyy): ");
                String date = sc.nextLine();

                bookingService.createBooking(new Booking(user.getEmail(), vehicleNo, service, date, "Pending"));
            } else if (c == 2) {
                List<Booking> bookings = bookingService.getAllBookings();
                for (Booking b : bookings) {
                    if (b.getUserEmail().equals(user.getEmail())) {
                        System.out.println(b.getVehicleNo() + " | " + b.getServiceName() + " | " + b.getDate() + " | " + b.getStatus());
                    }
                }
            } else if (c == 3) {
                System.out.println("Logged out successfully.");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void adminLogin(AdminService adminService, BookingService bookingService) {
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();

        if (!adminService.login(username, password)) {
            System.out.println("❌ Invalid admin credentials.");
            return;
        }

        while (true) {
            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. View All Bookings");
            System.out.println("2. Update Booking Status");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                adminService.viewAllBookings(bookingService);
            } else if (ch == 2) {
                System.out.print("Enter User Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Vehicle Number: ");
                String vno = sc.nextLine();
                System.out.print("Enter New Status: ");
                String status = sc.nextLine();
                bookingService.updateStatus(email, vno, status);
                System.out.println("✅ Status updated successfully!");
            } else if (ch == 3) {
                System.out.println("Logged out.");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
