package Stratgies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> map = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        if (move == null) {
            System.out.println("Move is null");
            return false;
        }

        if (move.getCell() == null) {
            System.out.println("Move's cell is null");
            return false;
        }

        int row = move.getCell().getRow();
        System.out.println("Row: " + row);

        if (move.getPlayer() == null) {
            System.out.println("Move's player is null");
            return false;
        }

        Symbol symbol = move.getPlayer().getSymbol();
        System.out.println("Symbol: " + symbol);

        if (board == null) {
            System.out.println("Board is null");
            return false;
        }

        // Check if row exists in the map, if not create a new entry
        Map<Symbol, Integer> countMap = map.get(row);
        if (countMap == null) {
            System.out.println("Count map not found for row: " + row + ". Initializing a new map.");
            countMap = new HashMap<>();
            map.put(row, countMap);
        }

        // Check if symbol count exists, if not start with 0
        Integer countOfSymbol = countMap.get(symbol);
        if (countOfSymbol == null) {
            System.out.println("No count found for symbol: " + symbol + ". Initializing count to 0.");
            countOfSymbol = 0;
        }

        // Update the count of the symbol
        countOfSymbol += 1;
        System.out.println("Updated count for symbol " + symbol + ": " + countOfSymbol);

        // Put the updated count back into the map
        countMap.put(symbol, countOfSymbol);

        // Check if the count equals the board size, which means a win
        if (countOfSymbol == board.getSize()) {
            System.out.println("We have a winner with symbol: " + symbol);
            return true;
        }

        System.out.println("No winner yet.");
        return false;
    }

    @Override
    public void handleUndo(Move move, Board board) {
        if (move == null) {
            System.out.println("Move is null");
            return;
        }

        if (move.getCell() == null) {
            System.out.println("Move's cell is null");
            return;
        }

        int row = move.getCell().getRow();
        System.out.println("Row: " + row);

        if (move.getPlayer() == null) {
            System.out.println("Move's player is null");
            return;
        }

        Symbol symbol = move.getPlayer().getSymbol();
        System.out.println("Symbol: " + symbol);

        Map<Symbol, Integer> countMap = map.get(row);
        if (countMap == null) {
            System.out.println("Count map is null for row: " + row);
            return;
        }

        Integer countOfSymbol = countMap.get(symbol);
        if (countOfSymbol == null) {
            System.out.println("No count found for symbol: " + symbol + " in row: " + row);
            return;
        }

        // Update the count by decreasing it
        countMap.put(symbol, countOfSymbol - 1);
        System.out.println("Updated count for symbol " + symbol + " in row " + row + ": " + (countOfSymbol - 1));
    }
}

