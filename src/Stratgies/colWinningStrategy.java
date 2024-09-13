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
        int col = move.getCell().getCol();
        Symbol symbol =  move.getPlayer().getSymbol();

        Map<Symbol, Integer> countMap = map.getOrDefault(col, new HashMap<>());

        int countOfSymbol = countMap.getOrDefault(symbol, 0) + 1;
        countMap.put(symbol, countOfSymbol);

        if (countOfSymbol == board.getSize()) {
            return  true;
        }

        return false;
    }
}
