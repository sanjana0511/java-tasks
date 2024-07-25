import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamination{

    private static Map<String, String> users = new HashMap<>();
    private static Map<String, String> profiles = new HashMap<>();
    private static boolean isLoggedIn = false;
    private static String currentUser = "";
    private static int timerDuration = 60; // exam duration in seconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        users.put("admin", "admin123"); // Default user
        profiles.put("admin", "Admin User");

        while (true) {
            System.out.println("\n1. Login\n2. Update Profile and Password\n3. Take Exam\n4. Logout\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    updateProfileAndPassword(scanner);
                    break;
                case 3:
                    if (isLoggedIn) {
                        takeExam(scanner);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    logout();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            isLoggedIn = true;
            currentUser = username;
            System.out.println("Login successful. Welcome, " + profiles.get(username));
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateProfileAndPassword(Scanner scanner) {
        if (isLoggedIn) {
            System.out.print("Enter new profile name: ");
            String profileName = scanner.nextLine();
            profiles.put(currentUser, profileName);

            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            users.put(currentUser, newPassword);

            System.out.println("Profile and password updated successfully.");
        } else {
            System.out.println("Please login first.");
        }
    }

    private static void takeExam(Scanner scanner) {
        System.out.println("Starting exam... You have " + timerDuration + " seconds.");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Auto-submitting answers...");
                submitExam();
            }
        };
        timer.schedule(task, timerDuration * 1000);

        System.out.println("Question 1: What is 2 + 2?");
        System.out.println("a) 3\nb) 4\nc) 5\nd) 6");
        System.out.print("Select your answer: ");
        String answer1 = scanner.nextLine();

        timer.cancel(); // Cancel the timer since the user has submitted the exam
        System.out.println("Exam submitted. Your answer was: " + answer1);
    }

    private static void submitExam() {
        logout();
        System.exit(0);
    }

    private static void logout() {
        if (isLoggedIn) {
            System.out.println("Logging out...");
            isLoggedIn = false;
            currentUser = "";
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}