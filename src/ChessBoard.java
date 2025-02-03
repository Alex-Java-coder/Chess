public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; //создание поля
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                if (board[startLine][startColumn].getSymbol().equals("K") ||  //проверка позиции для рокировки
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    board[startLine][startColumn].check = false;
                }

                board[endLine][endColumn] = board[startLine][startColumn]; //если фигура может ходить, мы двигаем фигуру
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //вывод доски
        System.out.println("Ход " + nowPlayer);
        System.out.println();
        System.out.println("Игрок 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Игрок 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && //проверка короля и ладьи
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              //еще не двигались
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { //проверка позиции, не под атакой
                    board[0][4] = null;
                    board[0][1] = new King("White");   //перемещаем короля
                    board[0][1].check = false;
                    board[0][0] = null;
                    board[0][2] = new Rook("White");   //перемещаем ладью
                    board[0][2].check = false;
                    nowPlayer = "Black";  //следующий ход
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && //проверка короля и ладьи
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              //еще не двигались
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { //проверка позиции, не под атакой
                    board[7][4] = null;
                    board[7][1] = new King("Black");   //перемещаем короля
                    board[7][1].check = false;
                    board[7][0] = null;
                    board[7][2] = new Rook("Black");   //перемещаем ладью
                    board[7][2].check = false;
                    nowPlayer = "White";  //следующий ход
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && //проверка короля и ладьи
                    board[0][6] == null && board[0][5] == null) {                             //еще не двигались
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { //проверка позиции, не под атакой
                    board[0][4] = null;
                    board[0][6] = new King("White");   //перемещаем короля
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   //перемещаем ладью
                    board[0][5].check = false;
                    nowPlayer = "Black";  //следующий ход
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && //проверка короля и ладьи
                    board[7][6] == null && board[7][5] == null) {                             //еще не двигались
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { //проверка позиции, не под атакой
                    board[7][4] = null;
                    board[7][6] = new King("Black");   //перемещаем короля
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   //перемещаем ладью
                    board[7][5].check = false;
                    nowPlayer = "White";  //следующий ход
                    return true;
                } else return false;
            } else return false;
        }
    }
}
