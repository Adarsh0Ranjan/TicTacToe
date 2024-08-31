package Controllers;


import Models.Game;
import Models.GameState;
import Models.Player;
import Stratgies.WinningStrategy;

import java.util.List;

// Controllers are stateless.
public class GameController {

    public Game startGame(int dimensionOfTheBoard,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies
                          ) {
        return null;
    }

    public void makeMove(Game game) {

    }

    public void getStatus(Game game) {

    }

    public GameState checkState(Game game) {
        return null;
    }

    public Player  getWinner(Game game) {
        return  null;
    }

    public void printBoard(Game game) {

    }

    public void undo(Game game) {

    }
}
