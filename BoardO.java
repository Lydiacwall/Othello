//Συμμετέχοντες : ΟΥΑΛΛΑΣ ΛΥΔΙΑ ΧΡΙΣΤΙΝΑ 3200125
//                ΧΑΡΑΛΑΜΠΟΣ ΚΑΡΑΚΩΣΤΑΣ 3200065
//                ΜΙΛΤΙΑΔΗΣ ΤΣΟΛΚΑΣ 3200213
import java.util.ArrayList;

public class BoardO {
    public static final int black = 1; // Αρχικοποίηση τιμής του black
    public static final int white = -1; // Αρχικοποίηση τιμής του white
    public static final int EMPTY = 0; // Τιμή για τις άδειες θέσεις στο πίνακα

    private int[][] othelloBoard; // Πίνακας παιχνιδιού
    private int lastPlayer; // Ο τελευταίος παίκτης που έπαιξε
    private Move lastMove; // Η τελευταία κίνηση που πραγματοποιήθηκε

    BoardO() { // Δημιουργία του πίνακα
        this.lastMove = new Move();
        this.lastPlayer = white; // Αρχικοποιείται ο τελευταίος παίκτης ως white για να παίξει πρώτος ο black
        this.othelloBoard = new int[8][8];
        for (int i = 0; i < this.othelloBoard.length; i++) {
            for (int j = 0; j < this.othelloBoard.length; j++) {
                this.othelloBoard[i][j] = EMPTY;
            }
        }
        // Αρχική κατάσταση του παιχνιδιού
        this.othelloBoard[3][3] = white;
        this.othelloBoard[4][4] = white;
        this.othelloBoard[3][4] = black;
        this.othelloBoard[4][3] = black;
    }

    BoardO(BoardO board)  // Χρησιμοποιείται στο δέντρο της minimax, δημιουργεί έναν νέο παρόμοιο πίνακα από έναν υπάρχον
    {                      
        this.lastMove = board.lastMove;
        this.lastPlayer = board.lastPlayer;
        this.othelloBoard = new int[8][8];
        for (int i = 0; i < this.othelloBoard.length; i++) {
            for (int j = 0; j < this.othelloBoard.length; j++) {
                this.othelloBoard[i][j] = board.othelloBoard[i][j];
            }
        }
    }

    void makeMove(int row, int col, int color) { // Πραγματοποιείται μια κίνηση
        if (isValidMove(row, col, color)) { // Ελέγχεται αν η κίνηση είναι έγκυρη
            this.othelloBoard[row][col] = color;
            this.lastMove = new Move(row, col);
            this.lastPlayer = color;
            this.flipColors(); // Καλείται συνάρτηση που ενημερώνει τα πιόνια που άλλαξαν χρ΄ωμα
        } else // Περίπτωση μη έγκυρης κίνησης
            System.out.println("invalid move");

    }

    // ------------------------------------------------------ isValidMove
    // ---------------------------------------------------------------------
    public boolean isValidMove(int row, int col, int color) {
        if ((row > 7) || (col > 7) || (row < 0) || (col < 0)) // Ελέγχουμε αν τα row,col είναι στα όρια του πίνακα
            return false;

        if (this.othelloBoard[row][col] != EMPTY) // Ελέγχουμε αν η θέση είναι άδεια
            return false;

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (!((i > 7) || (j > 7) || (i < 0) || (j < 0))) // Ελέγχουμε αν τα i,j είναι στα όρια του πίνακα
                {

                    if (!(row == i && col == j) && (this.othelloBoard[i][j] != EMPTY)) // Δεν θέλουμε να ελέγξουμε το
                                                                                       // χρώμα άδειας θέσης ή αυτής που
                                                                                       // θέλουμε να παίξουμε
                    {
                        if (this.othelloBoard[i][j] != color) // Εάν γειτονεύει με διαφορετικό χρώμα ελέγχουμε την
                                                              // ευθεία της κατεύθυνσης του
                        {
                            int b_row = i - (row - i); // Παίρνουμε την διαφορά row-i και την αφαιρούμε από το i για να
                                                       // βρούμε την επόμενη θέση
                            int b_col = j - (col - j);
                            if (!((b_row > 7) || (b_col > 7) || (b_row < 0) || (b_col < 0))) // Ελέγχουμε αν τα
                                                                                             // b_row,b_col
                                                                                             // είναι στα όρια του
                                                                                             // πίνακα
                            {
                                if (checker(b_row, b_col, color, row - i, col - j)) {// Eλέγχουμε αν υπάρχει
                                    // άλλη θέση του ίδιου χρώματος (άρα
                                    // είναι έγκυρη κίνηση)
                                    return true;
                                } // Εάν δεν είναι έγκυρο ελέχγουμε την επόμενη θέση
                            }
                        }
                    }
                }
            }
        }
        return false; // Εάν δεν βρούμε έγκυρη κίνηση επιστρέφουμε false
    }

    private boolean checker(int row, int col, int color, int dr, int dc) {// dr = Η διαφορά row και i / dc = Η διαφορά
                                                                          // col και j , έτσι ώστε να υπολογίσουμε την
                                                                          // επόμενη ΄θέση
        if (this.othelloBoard[row][col] == EMPTY) { // Εάν η θέση είναι άδεια δεν είναι έγκυρη
            return false;
        } else if (this.othelloBoard[row][col] == color) {// Εάν βρήκαμε το ίδιο χρώμα η κίνηση είναι έγκυρη
            return true;
        } else if (this.othelloBoard[row][col] != color) {// Εάν βρήκαμε διαφορετικό χρώμα ελέγχουμε την επόμενη θέση
            row = row - dr; // Υπολογίζουμε τις συντεταγμένες της επόμενης θέσης
            col = col - dc;
            if (!((row > 7) || (col > 7) || (row < 0) || (col < 0))) { // Ελέγχουμε αν τα row,col είναι στα όρια του
                                                                       // πίνακα
                boolean l = checker(row, col, color, dr, dc); // Αναδρομική κλήση της checker
                if (!l)
                    return false;
            } else {
                return false; // Είμαστε στα όρια του πίνακα
            }
        }
        return true;
    }

    // ----------------------------------------------------- FLIPPER
    // --------------------------------------------------------------------------
    void flipColors() {
        int row = lastMove.getRow(); // Παίρνουμε την γραμμή της τελευταίας κίνησης, αφού το lastMove έχει καθοριστεί
                                     // εφόσον είναι έγκυρη κίνηση
        int col = lastMove.getCol(); // Παίρνουμε την στήλη της τελευταίας κίνησης, αφού το lastMove έχει καθοριστεί
                                     // εφόσον είναι έγκυρη κίνηση
        int color = lastPlayer; // Παίρνουμε το χρ΄ωμα

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (!((i > 7) || (j > 7) || (i < 0) || (j < 0))) // Ελέγχουμε αν τα i,j είναι στα όρια του πίνακα
                {
                    if (!(row == i && col == j) && (this.othelloBoard[i][j] != EMPTY)) // Δεν θέλουμε να ελέγξουμε
                                                                                       // τοχρώμα άδειας θέσης ή αυτής
                                                                                       // που θέλουμε να παίξουμε
                    {
                        if (this.othelloBoard[i][j] != color) // Εάν βρούμε διαφορετικό χρώμα πρέπει να ελέγξουμε την
                                                              // γραμμή
                        {
                            flip(i, j, color, row - i, col - j); // Ξεκινάμε το flip των πιονιών που πρέπει να αλλάξουν
                                                                 // χρώμα
                        }
                    }
                }
            }
        }
    }

    public boolean flip(int row, int col, int color, int dr, int dc) {
        int m = 0;// Μετρητής πιονιών που πρέπει να αλλάξουν χρώμα
        int k = 0; // Μετρητής αλλαγμένων πιονιών
        int flipped[] = new int[50]; // Πίνακας με row και col πιονιών που πρέπει να αλλάξουν χρώμα
        for (int h = 0; h < 50; h++) // Αρχικοποίηση πίνακα
        {
            flipped[h] = -1;
        }
        // Αποθηκεύουμε τις συντεταγμένες της πρώτης θέσης με διαφορετικό χρώμσ
        flipped[k] = row;
        k = k + 1;
        flipped[k] = col;
        m = m + 1; // Αύξηση μετρητή
        k = k + 1; // Αύξηση μετρητή

        row = row - dr;
        col = col - dc;
        if (!((row > 7) || (col > 7) || (row < 0) || (col < 0))) // Ελέγχουμε αν τα row,col είναι στα όρια του πίνακα
        {
            while (true) { // Επαναλαμβάνουμε την διαδικασία
                if (this.othelloBoard[row][col] == EMPTY) { // Εάν είναι άδειο δεν είναι έγκυρο
                    return false;
                } else if (this.othelloBoard[row][col] == color) {// Σταματάμε την αναζήτηση όταν βρούμε ίδιου χρώματος
                                                                  // πιόνι
                    k = 0;// Γράμμη
                    int l = 1;// Στ΄ήλη
                    for (int i = 1; i <= m; i++) {// Αλλάζουμε χρώμα στα m πιόνια που βρήκαμε
                        this.setRowCol(flipped[k], flipped[l], color);
                        k = k + 2;// Αυξάνουμε τους μετρητές
                        l = l + 2;
                    }
                    return true;
                } else if (this.othelloBoard[row][col] != color) {// Έλεγχος επόμενης θέσης σε περίπτωση διαφορετικού
                                                                  // χρώματος
                    flipped[k] = row; // Αποθήκευση συντεταγμένων
                    k = k + 1;
                    flipped[k] = col;
                    k = k + 1;
                    row = row - dr; // Υπολογισμός συντεταγμένων επόμενης θέσης
                    col = col - dc;
                    if (!((row > 7) || (col > 7) || (row < 0) || (col < 0))) { // Ελέγχουμε αν τα row,col είναι στα όρια
                                                                               // του πίνακα
                        m = m + 1;
                    } else
                        return false; // Όρια πίνακα
                }
            }

        } else
            return false;
    }

    // ---------------------------------------------
    // getChildren----------------------------------------------------------------------------------
    ArrayList<BoardO> getChildren(int color) { // Δημιουργία λίστας για την υλοποίηση του δέντρου της minimax
        ArrayList<BoardO> children = new ArrayList<>();
        for (int row = 0; row < this.othelloBoard.length; row++) {
            for (int col = 0; col < this.othelloBoard.length; col++) {
                if (this.isValidMove(row, col, color)) {
                    BoardO child = new BoardO(this);
                    child.makeMove(row, col, color);
                    children.add(child);
                }
            }
        }
        return children;
    }

    int evaluate() { // Υπολογισμός του score του παιχνιδιού. Χρησιμοποείται μόνο ένας αθροιστής
                     // γιατί το score προκύπτει αθροιστικά προσθέτοντας τα -1(white) και τα 1(black)
                     // του πίνακα
        int sum = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sum = sum + this.othelloBoard[i][j];
            }
        }

        return sum;
    }

    boolean isTerminal(int color) { // Ελέγχεται αν ο πίνακας είναι γεμ΄άτος και αν ο παίκτης color έχει περιθώρια
                                    // πραγματόποιησης κάποιας κίνησης
        boolean emptyOrValid = true; // Μετάβλητη ελέγχου αν ο πίνακας έχει γεμίσει ή όχι και αν μπορούν να γίνουν
                                     // έγκυρες κινήσεις
        for (int row = 0; row < this.othelloBoard.length; row++) {
            for (int col = 0; col < this.othelloBoard.length; col++) {
                if (this.othelloBoard[row][col] == EMPTY) {
                    if (this.isValidMove(row, col, color)) {
                        emptyOrValid = false;
                    }
                }
            }
        }
        return emptyOrValid;
    }

    boolean isTerminalSameColor() { // Ελέγχεται αν ο πίνακας είναι γεμ΄άτος και αν έχουμε πιόνια μόνο ένος χρώματος
        boolean empty = true; // Μετάβλητη ελέγχου αν ο πίνακας έχει γεμίσει ή όχι
        int oneColor = 0;
        boolean flag = false; // Έχουμε πιόνια μόνο ενός χρώματος
        for (int row = 0; row < this.othelloBoard.length; row++) {
            for (int col = 0; col < this.othelloBoard.length; col++) {
                if (this.othelloBoard[row][col] == EMPTY) {
                    empty = false;
                } else {
                    if (!flag) {
                        if (oneColor == 0) // Αν δεν έχει πάρει τιμή
                        {
                            oneColor = this.othelloBoard[row][col]; // Παίρνει την τιμή του 1ου που βρούμε
                        }
                        if (this.othelloBoard[row][col] == (oneColor * (-1))) {
                            flag = true; // Βρήκαμε και το αντίθετο χρώμα , άρα δεν έχουν όλα το ίδιο χρώμα
                        }
                    }
                }
            }
        }
        if (!flag) // Έχουμε πιόνια μόνο ενός χρώματος
        {
            empty = true;
        }
        return empty;
    }

    // isTerminal() που χρησιμοποείται στην while της main χωρίς color όρισμα που
    // ελέγχει μόνο αν είναι γεμάτος ο πίνακας
    boolean isTerminal() {
        for (int row = 0; row < this.othelloBoard.length; row++) {
            for (int col = 0; col < this.othelloBoard.length; col++) {
                if (this.othelloBoard[row][col] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    void print() { // Εκτυπώνει τον πίνακα του παιχνιδιού
        int m = 0;
        System.out.println("*****************");
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int row = 0; row < 8; row++) {
            System.out.print(m + " ");
            m++;
            for (int col = 0; col < 8; col++) {
                switch (this.othelloBoard[row][col]) {
                    case black -> System.out.print("B ");
                    case white -> System.out.print("W ");
                    case EMPTY -> System.out.print("- ");
                    default -> {
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("*****************");
    }

    Move getLastMove() {
        return this.lastMove;
    }

    int getLastPlayer() {
        return this.lastPlayer;
    }

    int[][] getOBoard() {
        return this.othelloBoard;
    }

    void setRowCol(int row, int col, int color) {
        this.othelloBoard[row][col] = color;
    }

    void setLastMove(Move lastMove) {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }

    void setLastPlayer(int lastPlayer) {
        this.lastPlayer = lastPlayer;
    }
}