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
                          ) throws Exception {
        return Game.getBuilder()
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setDimenSion(dimensionOfTheBoard)
                .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public Player  getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }
}
