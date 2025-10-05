package theater;

import static theater.SeatingManager.clearScreen;

public class SeatingChart {
    private char[][] seats;

    public SeatingChart(int row, int col){
        seats = new char[row][col];
        addEmptySeats();
    }
    public void addEmptySeats(){
        for (int row = 0; row < seats.length; row ++){
            for (int col = 0; col < seats[row].length; col ++){
                seats[row][col] = 'O';
            }
        }
    }

    // Display chart with row/col labels
    public void displaySeatingChart() {
        clearScreen();
        System.out.println("  === Theater Seating Chart ===");

        // Print number across the top for easy reference
        System.out.print("   ");
        for( int col = 0; col < seats[0].length; col ++){
                System.out.printf("%2d ", col + 1); // formats digits and space across top of chart
        }

        System.out.println();

        // Print numbers on left side and seating chart
        for ( int row = 0; row < seats.length; row ++){
            System.out.printf("%2d  ", row +1); // Formats digits and space along left up and down
            for (int col = 0; col < seats[row].length; col ++){
                System.out.print(seats[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println("Legend: O = Open, X = Reserved");
    }

    public boolean reserveSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return false;
        }
        if (seats[row][col] == 'X') {
            return false;
        }
        seats[row][col] = 'X';
        return true;
    }

    public boolean cancelSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return false;
        }
        if (seats[row][col] == 'O') {
            return false;
        }
        seats[row][col] = 'O';
        return true;
    }

    public boolean isSeatTaken(int row, int col) {
        return seats[row][col] == 'X';
    }

    public int getRows() {
        return seats.length;
    }

    public int getCols() {
        return seats[0].length;
    }

    public boolean isFull() {
        for (int r = 0; r < seats.length; r++) {
            for (int c = 0; c < seats[r].length; c++) {
                if (seats[r][c] == 'O') return false;
            }
        }
        return true;
    }


}
