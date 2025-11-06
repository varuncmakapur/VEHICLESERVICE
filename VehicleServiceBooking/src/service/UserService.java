package service;

import java.io.*;
import java.util.*;
import model.User;

public class UserService {
    private static final String USER_FILE = "data/users.txt";

    public void registerUser(User user) {
        try (FileWriter fw = new FileWriter(USER_FILE, true)) {
            fw.write(user.toString() + "\n");
            System.out.println("✅ User registered successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving user data.");
        }
    }

    public User login(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading user data.");
        }
        return null;
    }
}
