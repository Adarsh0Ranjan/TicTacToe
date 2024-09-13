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
        int row = move.getCell().getRow();
        Symbol symbol =  move.getPlayer().getSymbol();

        Map<Symbol, Integer> countMap = map.getOrDefault(row, new HashMap<>());

        int countOfSymbol = countMap.getOrDefault(symbol, 0) + 1;
        countMap.put(symbol, countOfSymbol);

        if (countOfSymbol == board.getSize()) {
            return  true;
        }

        return false;
    }

    public void handleUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol =  move.getPlayer().getSymbol();

        Map<Symbol, Integer> countMap = map.get(row);

        int countOfSymbol = countMap.get(symbol);
        countMap.put(symbol, countOfSymbol - 1);
    }
}
