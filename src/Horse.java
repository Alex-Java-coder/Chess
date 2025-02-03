public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            if (line != toLine && column != toColumn && (chessBoard.board[toLine][toColumn] == null || // проверка, что конь
                    !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&                   // не может выйти
                    chessBoard.board[line][column] != null) {                                          // позиция пуста
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

                //все позиции для коня
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}};

                for (int i = 0; i < positions.length; i++) {
                    if (positions[i][0] == toLine && positions[i][1] == toColumn)
                        return true;  //проверка по линии и колонки
                }                                                                               // в позиции
            }
        } else return false;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    public boolean checkPos(int pos) {   // проверка, что позиция верна
        if (pos >= 0 && pos <= 7) return true;
        else return false;
    }
}
