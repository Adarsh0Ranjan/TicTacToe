package Stratgies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class colWinningStrategy implements WinningStrategy {
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

        int col = move.getCell().getCol();
        System.out.println("Column: " + col);

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

        // Check if column exists in the map, if not create a new entry
        Map<Symbol, Integer> countMap = map.get(col);
        if (countMap == null) {
            System.out.println("Count map not found for column: " + col + ". Initializing a new map.");
            countMap = new HashMap<>();
            map.put(col, countMap);
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

        int col = move.getCell().getCol();
        System.out.println("Column: " + col);

        if (move.getPlayer() == null) {
            System.out.println("Move's player is null");
            return;
        }

        Symbol symbol = move.getPlayer().getSymbol();
        System.out.println("Symbol: " + symbol);

        Map<Symbol, Integer> countMap = map.get(col);
        if (countMap == null) {
            System.out.println("Count map is null for column: " + col);
            return;
        }

        Integer countOfSymbol = countMap.get(symbol);
        if (countOfSymbol == null) {
            System.out.println("No count found for symbol: " + symbol + " in column: " + col);
            return;
        }

        // Update the count by decreasing it
        countMap.put(symbol, countOfSymbol - 1);
        System.out.println("Updated count for symbol " + symbol + " in column " + col + ": " + (countOfSymbol - 1));
    }

}
