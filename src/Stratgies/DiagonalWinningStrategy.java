package Stratgies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    Map<Symbol, Integer> leftDiag = new HashMap<>();
    Map<Symbol, Integer> rightDiag = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        boolean isWinner = false;

        if(row == col) {
            int countOfSymbol = leftDiag.getOrDefault(symbol, 0) + 1;
            leftDiag.put(symbol, countOfSymbol);

            if(countOfSymbol == board.getSize()) {
                isWinner = true;
            }
        }

        if(row + col == board.getSize() - 1) {
            int countOfSymbol = rightDiag.getOrDefault(symbol, 0) + 1;
            rightDiag.put(symbol, countOfSymbol);

            if(countOfSymbol == board.getSize()) {
                isWinner = true;
            }
        }
        return isWinner;
    }

    public void handleUndo(Move move, Board board) {
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        Symbol symbol =  move.getPlayer().getSymbol();
        if(row == col) {
            int countOfSymbol = leftDiag.get(symbol);
            leftDiag.put(symbol, countOfSymbol -1);
        }

        if(row + col == board.getSize() - 1) {
            int countOfSymbol = rightDiag.get(symbol);
            rightDiag.put(symbol, countOfSymbol - 1);
        }
    }
}
