package Stratgies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    private Map<Symbol, Integer> leftDiag = new HashMap<>();
    private Map<Symbol, Integer> rightDiag = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        if (move == null || move.getCell() == null || move.getPlayer() == null || board == null) {
            System.out.println("Invalid move or board");
            return false;
        }

        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        boolean isWinner = false;

        System.out.println("Move: Row = " + row + ", Col = " + col + ", Symbol = " + symbol);

        // Check for left diagonal (row == col)
        if (row == col) {
            // Check if symbol exists in left diagonal map
            Integer countOfSymbol = leftDiag.get(symbol);
            if (countOfSymbol == null) {
                System.out.println("No count found for symbol in left diagonal. Initializing to 0.");
                countOfSymbol = 0;
            }

            countOfSymbol += 1;
            leftDiag.put(symbol, countOfSymbol);
            System.out.println("Updated count for left diagonal: " + countOfSymbol);

            if (countOfSymbol == board.getSize()) {
                System.out.println("We have a winner on the left diagonal with symbol: " + symbol);
                isWinner = true;
            }
        }

        // Check for right diagonal (row + col == board size - 1)
        if (row + col == board.getSize() - 1) {
            // Check if symbol exists in right diagonal map
            Integer countOfSymbol = rightDiag.get(symbol);
            if (countOfSymbol == null) {
                System.out.println("No count found for symbol in right diagonal. Initializing to 0.");
                countOfSymbol = 0;
            }

            countOfSymbol += 1;
            rightDiag.put(symbol, countOfSymbol);
            System.out.println("Updated count for right diagonal: " + countOfSymbol);

            if (countOfSymbol == board.getSize()) {
                System.out.println("We have a winner on the right diagonal with symbol: " + symbol);
                isWinner = true;
            }
        }

        return isWinner;
    }

    @Override
    public void handleUndo(Move move, Board board) {
        if (move == null || move.getCell() == null || move.getPlayer() == null) {
            System.out.println("Invalid move");
            return;
        }

        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        System.out.println("Undo Move: Row = " + row + ", Col = " + col + ", Symbol = " + symbol);

        // Handle undo for left diagonal (row == col)
        if (row == col) {
            Integer countOfSymbol = leftDiag.get(symbol);
            if (countOfSymbol != null && countOfSymbol > 0) {
                leftDiag.put(symbol, countOfSymbol - 1);
                System.out.println("Decremented left diagonal count for symbol " + symbol + ": " + (countOfSymbol - 1));
            } else {
                System.out.println("Left diagonal count for symbol " + symbol + " is null or 0.");
            }
        }

        // Handle undo for right diagonal (row + col == board size - 1)
        if (row + col == board.getSize() - 1) {
            Integer countOfSymbol = rightDiag.get(symbol);
            if (countOfSymbol != null && countOfSymbol > 0) {
                rightDiag.put(symbol, countOfSymbol - 1);
                System.out.println("Decremented right diagonal count for symbol " + symbol + ": " + (countOfSymbol - 1));
            } else {
                System.out.println("Right diagonal count for symbol " + symbol + " is null or 0.");
            }
        }
    }
}

