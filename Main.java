import java.util.*;

public class Main {
    public static final int black = 1;
    public static final int white = -1;
    public static final int EMPTY = 0;
    public static int other = 0;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BoardO board = new BoardO();
        Player playerW = new Player(0, 0);
        Player playerB = new Player(0, 0);
        System.out.println(" Type please the max depth");
        int k = sc.nextInt();
        int row = -1;
        int col = -1;
        String a = sc.nextLine();

        while (true) {
            System.out.println(" Do you want to play first? Type YES or NO");
            a = sc.nextLine();
            if (a.equalsIgnoreCase("YES")) {
                playerW.setPlayer(k, BoardO.white);
                other = black;// Αποθηκεύουμε το χρώμα του παίκτη που δεν είναι ο υπολογιστής
                break;
            } else if (a.equalsIgnoreCase("NO")) {
                playerB.setPlayer(k, BoardO.black);
                other = white; // Αποθηκεύουμε το χρώμα του παίκτη που δεν είναι ο υπολογιστής
                break;
            } else
                System.out.println("Wrong answer. Type again");
        }
        // Αρχικοποιείται ο τελευταίος παίκτης ως white για να παίξει πρώτος ο black
        board.setLastPlayer(BoardO.white);
        board.print();

        while (!board.isTerminal()) { // Όσο ο πίνακας δεν είναι γεμάτος
            if (board.isTerminalSameColor()) { // Όσο δεν έχουν όλα το ίδιο χρώμα
                break;
            }
            if (board.isTerminal(board.getLastPlayer() * (-1))) { // Εάν ένας παίκτης δεν έχει περιθώριο να κάνει κάποια
                                                                  // κίνηση ξαναπάιζει ο άλλος
                board.setLastPlayer(board.getLastPlayer() * (-1));
            }
            switch (board.getLastPlayer()) {
                case BoardO.white:
                    System.out.println("black plays");
                    if (other == black) { // Δίνονται row και col από το πληκτρολόγιο και ελέγχεται η εγκυρότητά τους
                        System.out.println("Type row  ");
                        while (row == -1) {
                            if (sc.hasNextInt()) {
                                row = sc.nextInt();
                            } else {
                                System.out.println("Not a valid Integer");
                                sc.next();
                            }
                        }
                        System.out.println("Type column  ");
                        while (col == -1) {
                            if (sc.hasNextInt()) {
                                col = sc.nextInt();
                            } else {
                                System.out.println("Not a valid Integer");
                                sc.next();
                            }
                        }
                        board.makeMove(row, col, BoardO.black);
                        row = -1; // Αρχικοποίηση ξανά των row,col ώστε να χρησιμοποιηθούν στην συνθήκη του
                                  // έλεγχου εγκυρότητας
                        col = -1;
                    } else {
                        // Σειρά του υπολογιστή
                        Move moveB = playerB.MiniMax(board);
                        board.makeMove(moveB.getRow(), moveB.getCol(), BoardO.black);
                    }
                    board.print();
                    System.out.println("The score is: " + board.evaluate());
                    break;
                case BoardO.black:
                    System.out.println("white plays");
                    if (other == white) { // Δίνονται row και col από το πληκτρολόγιο και ελέγχεται η εγκυρότητά τους
                        System.out.println("Type row  ");
                        while (row == -1) {
                            if (sc.hasNextInt()) {
                                row = sc.nextInt();
                            } else {
                                System.out.println("Not a valid Integer");
                                sc.next();
                            }
                        }
                        System.out.println("Type column  ");
                        while (col == -1) {
                            if (sc.hasNextInt()) {
                                col = sc.nextInt();
                            } else {
                                System.out.println("Not a valid Integer");
                                sc.next();
                            }
                        }
                        board.makeMove(row, col, BoardO.white);
                        row = -1; // Αρχικοποίηση ξανά των row,col ώστε να χρησιμοποιηθούν στην συνθήκη του
                                  // έλεγχου εγκυρότητας
                        col = -1;
                    } else {
                        // Σειρά του υπολογιστή
                        Move moveB = playerW.MiniMax(board);
                        board.makeMove(moveB.getRow(), moveB.getCol(), BoardO.white);
                    }
                    board.print();
                    System.out.println("The score is: " + board.evaluate());
                    break;
                default:
                    break;
            }

        }
        if (board.evaluate() > 0) { // Εμφάνισης μηνύματος ανάδειξης του νικητή του παιχνιδιού με βάση την evaluate
            System.out.println("Congratulations!! The winner is Black");
        } else if (board.evaluate() < 0) {
            System.out.println("Congratulations!! The winner is White");
        } else {
            System.out.println("It's a tie!!");
        }
    }
}