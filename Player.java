////Συμμετέχοντες : ΟΥΑΛΛΑΣ ΛΥΔΙΑ ΧΡΙΣΤΙΝΑ 3200125
//                ΧΑΡΑΛΑΜΠΟΣ ΚΑΡΑΚΩΣΤΑΣ 3200065
//                ΜΙΛΤΙΑΔΗΣ ΤΣΟΛΚΑΣ 3200213
import java.util.ArrayList;

public class Player {
    private int maxDepth;// Μέγιστο βάθος δέντρου της minimax
    private int playerColor; // Χρώμα παίκτη του υπολογιστή

    Player(int maxDepth, int playerColor) {
        this.maxDepth = maxDepth;
        this.playerColor = playerColor;
    }

    void setPlayer(int maxDepth, int playerColor) {
        this.maxDepth = maxDepth;
        this.playerColor = playerColor;
    }

    Move MiniMax(BoardO board) {
        if (playerColor == BoardO.black) {
            // Εάν o Black παίζει, τότε θέλει να μεγιστοποιήσει την ευρετική τιμή
            return max(new BoardO(board), 0);
        } else {
            // Εάν το White παίζει, τότε θέλει να ελαχιστοποιησει την ευρετική τιμή
            return min(new BoardO(board), 0);
        }
    }

    /*
     * Οι συναρτήσεις max και min καλούνται η μία μετά την άλλη μέχρι να φτάσουν ένα
     * μέγιστο βάθος.
     * Δημιουργούμε ένα δέντρο χρησιμοποιώντας backtracking DFS.
     */
    Move max(BoardO board, int depth) {
        /*
         * Εάν το MAX καλείται σε μια κατάσταση που είναι τερματική ή μετά από ένα
         * μέγιστο βάθος,
         * τότε υπολογίζεται ένα ευρετικό στην κατάσταση και η κίνηση επιστρέφεται.
         */
        if (board.isTerminal(playerColor) || (depth == this.maxDepth)) {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }
        // Υπολογίζονται τα children-moves of the state.
        ArrayList<BoardO> children = board.getChildren(BoardO.black);
        Move maxMove = new Move(Integer.MIN_VALUE); // Βάζουμε τον μέγιστο κόμβο αρχικά στη μικρότερη τιμή
        for (BoardO child : children) {
            // Και για κάθε παιδί καλείται min, σε μικρότερο βάθος
            Move move = min(child, depth + 1);
            // Η μετακίνηση παιδιού με τη μεγαλύτερη τιμή επιλέγεται και επιστρέφεται με το
            // max.
            if (move.getValue() >= maxMove.getValue()) {
                maxMove.setRow(child.getLastMove().getRow());
                maxMove.setCol(child.getLastMove().getCol());
                maxMove.setValue(move.getValue());
            }
        }
        return maxMove;
    }

    // Παρόμοια λειτουργία της min με την max
    Move min(BoardO board, int depth) {
        if (board.isTerminal(playerColor) || (depth == this.maxDepth)) {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }
        ArrayList<BoardO> children = board.getChildren(BoardO.white);
        Move minMove = new Move(Integer.MAX_VALUE);
        for (BoardO child : children) {
            // Και για κάθε παιδί καλείται max, σε μικρότερο βάθος
            Move move = max(child, depth + 1);
            // Η μετακίνηση παιδιού με τη μικρότερη τιμή επιλέγεται και επιστρέφεται με το
            // min.
            if (move.getValue() <= minMove.getValue()) {
                minMove.setRow(child.getLastMove().getRow());
                minMove.setCol(child.getLastMove().getCol());
                minMove.setValue(move.getValue());
            }
        }
        return minMove;
    }
}