public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) && chessBoard.board[line][column] != null) { //проверка, что позиция на доске
            if (column == toColumn) { // проверка, что мы не рубим
                int dir;
                int start;

                if (color.equals("White")) {  // для белых
                    dir = 1;
                    start = 1;
                } else { // для черных
                    dir = -1;
                    start = 6;
                }

                if (line + dir == toLine) {  //сверка направления
                    return chessBoard.board[toLine][toColumn] == null;
                }

                if (line == start && line + 2 * dir == toLine) {
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null; // проверка, что позиция пуста
                }

            } else { // хотим срубить фигуру

                if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1) && // проверка, что фигура другого цвета
                        chessBoard.board[toLine][toColumn] != null) {
                    return !chessBoard.board[toLine][toColumn].getColor().equals(color);
                } else return false;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
