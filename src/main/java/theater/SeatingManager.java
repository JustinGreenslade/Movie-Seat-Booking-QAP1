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
        chart.displaySeatingChart();
        System.out.print("Enter row number: ");
        int row = scanner.nextInt() - 1;
        System.out.print("Enter seat number: ");
        int col = scanner.nextInt() - 1;
        scanner.nextLine();

        if (chart.reserveSeat(row, col)) {
            chart.displaySeatingChart();
            System.out.println("Seat (" + (row + 1) + ", " + (col + 1) + ") successfully reserved!");
            enterToContinue(scanner);
        } else {
            chart.displaySeatingChart();

            System.out.print("Seat taken or invalid. Suggesting another seat...");
            suggestAlternative(row);
            enterToContinue(scanner);
        }
    clearScreen();
    }

    public void cancelReservation() {
        chart.displaySeatingChart();
        System.out.print("Enter row number: ");
        int row = scanner.nextInt() - 1;
        System.out.print("Enter seat number: ");
        int col = scanner.nextInt() - 1;
        scanner.nextLine();

        if (chart.cancelSeat(row, col)) {
            chart.displaySeatingChart();
            System.out.println("Reservation for seat (" + (row + 1) + ", " + (col + 1) + ") successfully cancelled.");
            enterToContinue(scanner);
        } else {
            chart.displaySeatingChart();
            System.out.println("Seat (" + (row + 1) + ", " + (col + 1) + ") was not reserved.");
            enterToContinue(scanner);
        }

        clearScreen();
    }

    private void suggestAlternative(int row) {
        if (row >= 0 && row < chart.getRows()) {
            for (int c = 0; c < chart.getCols(); c++) {
                if (!chart.isSeatTaken(row, c)) {
                    System.out.println("Try Row " + (row + 1) + ", Seat " + (c + 1));
                    return;
                }
            }
        }

        for (int r = row + 1; r < chart.getRows(); r++) {
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
