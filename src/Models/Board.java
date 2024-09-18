package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;

    private List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        board = new ArrayList<>();

        for(int i = 0; i<size; i++) {
            List<Cell> cells = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                Cell cell = new Cell(i, j);
                cells.add(cell);
            }
            board.add(cells);
        }
    }

    public void printBoard() {
       for (List<Cell> row: board) {
           for(Cell cell: row) {
               cell.display();
           }

           System.out.println();
       }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
