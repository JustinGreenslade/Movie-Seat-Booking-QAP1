package theater;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SeatingChart chart = new SeatingChart(5, 10);
        SeatingManager manager = new SeatingManager(chart, scanner);

        boolean running = true;
        while (running){
            System.out.println("=== Movie Theater Reservation System ===");
            System.out.println("1. Display Seating Chart");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a seat Reservation");
            System.out.println("4. Exit");

            int choice;
            while (true){
                System.out.print("Select an option (1 - 4): ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 4) break;
                    else System.out.println("Invalid option. Please enter a number between 1 and 4.");
                } catch (Exception e){
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    chart.displaySeatingChart();
                    SeatingManager.enterToContinue(scanner);
                    SeatingManager.clearScreen();
                    break;
                case 2:
                    SeatingManager.clearScreen();
                    manager.reserveSeat();
                    break;
                case 3:
                    SeatingManager.clearScreen();
                    manager.cancelReservation();
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
}
