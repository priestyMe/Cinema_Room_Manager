package cinema;

import java.text.NumberFormat;
import java.util.Scanner;

public class Cinema {

    static int row;
    static int col;
    static int rowNum, colNum;
    static int ticket = 0;
    static int purchasedTickets = 0;
    static int currentIncome = 0;
    static double percentageOfSeats = 0;
    static int frontRow;
    static int backRow;
    static int totalIncome = 0;
    static char dollarSign = '$';

    static int frontRowSale = 0;
    static int backRowSale = 0;
    static int totalSale, thisSale = 0;

    public static void main(String[] args) {
        // Write your code here

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        col = sc.nextInt();
        int totalSeats = row * col;

        frontRow = row/2;
        backRow = row - frontRow;

        if (totalSeats <= 60) {
            totalIncome += row * col * 10;
        }

        if (totalSeats > 60) {
            frontRow = row/2;
            backRow = row - frontRow;
            totalIncome = (frontRow * col * 10) + (backRow * col * 8);
        }

        char[][] cinemaSeats = new char[row][col];

        fillSeats(cinemaSeats);

        int choice;

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    printSeats(cinemaSeats);
                    break;

                case 2:
                    buyATicket(dollarSign, sc, cinemaSeats, totalSeats);
                    break;
                case 3:
                    ticketStatistics(cinemaSeats, totalSeats,currentIncome,totalIncome);
                    break;

                case 0:
                    return;
            }

        } while (choice > 0);

    }

    public static void buyATicket(char dollarSign, Scanner sc, char[][] cinemaSeats, int totalSeats) {

        boolean emptySeat = true;

        do {
            System.out.println("Enter a row number:");
            rowNum = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            colNum = sc.nextInt();

            if ((rowNum > row) || (colNum > col)) {
                System.out.println("Wrong input!");
                //emptySeat = false;
                continue;
            }

            if ((cinemaSeats[rowNum - 1][colNum - 1]) == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                    (cinemaSeats[rowNum - 1][colNum - 1]) = 'B';
                    purchasedTickets++;
                    emptySeat = false;
            }
        } while (emptySeat);

        if (totalSeats <= 60) {
            ticket = 10;
            //totalIncome += row * col * 10;
        }

        if (totalSeats > 60) {
            frontRow = row/2;
            backRow = row - frontRow;
            //totalIncome += (frontRow * 10) + (backRow * 8);

            if (rowNum > frontRow) {
                ticket = 8;
            } else {
                ticket = 10;
            }
        }

        currentIncome += ticket;
        System.out.printf("Ticket price: %c%d", dollarSign,ticket);
        System.out.println();
    }


    public static void fillSeats (char[][] matrix) {

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                matrix[i][j] = 'S';
            }
        }
    }

    public static void printSeats (char[][] matrix) {

        System.out.println("Cinema:");
        System.out.print("  ");

        for (int x = 0; x < col; x++) {
            System.out.print(x + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < row; i++) {

            System.out.print(i + 1 + " ");
            //System.out.print(" ");
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void ticketStatistics(char[][] matrix, int totalSeats, int currentIncome, int totalIncome) {

        char percentSign = '%';

        percentageOfSeats = (purchasedTickets/(double)totalSeats) * 100;

        System.out.println("Number of purchased tickets:" + purchasedTickets);

        String ticketDetails = String.format("Percentage: %.2f%c", percentageOfSeats,percentSign);

        System.out.println(ticketDetails);
        System.out.println("Current income:" + dollarSign + currentIncome);
        System.out.println("Total income:" + dollarSign + totalIncome);

    }
}