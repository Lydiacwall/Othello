//Συμμετέχοντες : ΟΥΑΛΛΑΣ ΛΥΔΙΑ ΧΡΙΣΤΙΝΑ 3200125
//                ΧΑΡΑΛΑΜΠΟΣ ΚΑΡΑΚΩΣΤΑΣ 3200065
//                ΜΙΛΤΙΑΔΗΣ ΤΣΟΛΚΑΣ 3200213
public class Move {
    private int row; // Επιλογή γραμμής από τον χρήστη ή από τον υπολογιστή
    private int col; // Επιλογή στήλης από τον χρήστη ή από τον υπολογιστή
    private int value;

    Move() {
        this.row = -1;
        this.col = -1;
        this.value = 0;
    }

    Move(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = -1;
    }

    Move(int value) {
        this.row = -1;
        this.col = -1;
        this.value = value;
    }

    Move(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    int getValue() {
        return this.value;
    }

    void setRow(int row) {
        this.row = row;
    }

    void setCol(int col) {
        this.col = col;
    }

    void setValue(int value) {
        this.value = value;
    }
}