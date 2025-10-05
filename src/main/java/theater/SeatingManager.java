package theater;

import java.util.Scanner;

public class SeatingManager {
    private SeatingChart chart;
    private Scanner scanner;

    public SeatingManager(SeatingChart chart, Scanner scanner) {
        this.chart = chart;
        this.scanner = scanner;
    }

    public void reserveSeat() {
        if (chart.isFull()) {
            chart.displaySeatingChart();
            System.out.println("Sorry, the theater is fully booked. No seats available.");
            enterToContinue(scanner);
            clearScreen();
            return;
        }
        while (true){
            chart.displaySeatingChart();
            System.out.println("Enter row number (-1 to cancel): ");
            int inputRow = scanner.nextInt();
            if(inputRow == -1){
                System.out.println("Reservation process cancelled. Returning to main menu...");
                enterToContinue(scanner);
                scanner.nextLine();
                clearScreen();
                return;
            }
            int row = inputRow - 1;

            System.out.print("Enter seat number (-1 to cancel): ");
            int inputCol = scanner.nextInt();
            scanner.nextLine();
            if (inputCol == -1) {
                System.out.println("Reservation process cancelled. Returning to main menu...");
                enterToContinue(scanner);
                clearScreen();
                return;
            }
            int col = inputCol - 1;

            // Out of bounds check
            if(row < 0 || row >= chart.getRows() || col < 0 || col >= chart.getCols()) {
                chart.displaySeatingChart();
                System.out.println("Invalid seat selection. Please try again.");
                suggestAlternative(row);
                enterToContinue(scanner);
                clearScreen();
                continue;
        }

            if (chart.reserveSeat(row, col)) {
                chart.displaySeatingChart();
                System.out.println("Seat (" + (row + 1) + ", " + (col + 1) + ") successfully reserved!");
                enterToContinue(scanner);
                clearScreen();
                return;
            } else {
                chart.displaySeatingChart();
                System.out.print("Seat is taken. Suggesting another seat...");
                suggestAlternative(row);
                enterToContinue(scanner);
                clearScreen();
            }
        }
    }

    public void cancelReservation() {
        while (true) {
            chart.displaySeatingChart();
            System.out.print("Enter row number (-1 to cancel): ");
            int inputRow = scanner.nextInt();
            // if user wants to cancel reservation process
            if (inputRow == -1) {
                System.out.println("Cancellation process cancelled. Returning to main menu...");
                scanner.nextLine();
                enterToContinue(scanner);
                clearScreen();
                return;
            }
            int row = inputRow - 1;

            System.out.print("Enter seat number (-1 to cancel): ");
            int inputCol = scanner.nextInt();
            scanner.nextLine();
            if(inputCol == -1){
                System.out.println("Cancellation process cancelled. Returning to main menu...");
                enterToContinue(scanner);
                clearScreen();
                return;
            }
            int col = inputCol - 1;

            // Out of bounds check
            if (row < 0 || row >= chart.getRows() || col < 0 || col >= chart.getCols()) {
                chart.displaySeatingChart();
                System.out.println("Invalid seat! That seat does not exist.");
                enterToContinue(scanner);
                clearScreen();
                continue;
            }

            // try to cancel
            if (chart.cancelSeat(row, col)) {
                chart.displaySeatingChart();
                System.out.println("Reservation for seat (" + (row + 1) + ", " + (col + 1) + ") successfully cancelled.");
                enterToContinue(scanner);
                clearScreen();
                return;
            } else {
                chart.displaySeatingChart();
                System.out.println("Seat (" + (row + 1) + ", " + (col + 1) + ") was not reserved.");
                enterToContinue(scanner);
                clearScreen();
                return;
            }
        }
    }

    private void suggestAlternative(int row) {
        // trys same row first.
        if (row >= 0 && row < chart.getRows()) {
            for (int c = 0; c < chart.getCols(); c++) {
                if (!chart.isSeatTaken(row, c)) {
                    System.out.println("Try Row " + (row + 1) + ", Seat " + (c + 1));
                    return;
                }
            }
        }
        // if no seats in that row trys whole chart
        for (int r = 0; r < chart.getRows(); r++) {
            for (int c = 0; c < chart.getCols(); c++) {
                if (!chart.isSeatTaken(r, c)) {
                    System.out.println("Try Row " + (r + 1) + ", Seat " + (c + 1));
                    return;
                }
            }
        }

        System.out.println("No seats available.");
    }

    // enter to continue
    public static void enterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();

    }
    // clears screen for readability
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
