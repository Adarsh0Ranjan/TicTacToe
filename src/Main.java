import Controllers.GameController;
import Models.*;
import Stratgies.DiagonalWinningStrategy;
import Stratgies.RowWinningStrategy;
import Stratgies.WinningStrategy;
import Stratgies.colWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();

        Scanner scanner = new Scanner(System.in);
         int dimesion = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Adarsh", 1L, new Symbol('X'), PlayerType.HUMAN));
        players.add (new Bot("GPT", 2L, new Symbol('O'), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new colWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(dimesion, players, winningStrategies);

        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            System.out.println("Does anyone want to do an undo (y/n)");
            String undoAnswer = scanner.next();
            if(undoAnswer.equalsIgnoreCase("y")) {
                gameController.undo(game);
//                continue;
            } else {
                gameController.makeMove(game);
            }

        }

        gameController.printBoard(game);
        System.out.println("Game is finished");
        GameState gameState = gameController.checkState(game);
        if(gameState.equals(GameState.WIN)) {
            System.out.println("Winner is: " + gameController.getWinner(game).getName());
        } else if(gameState.equals(GameState.DRAW)) {
            System.out.println("Game Drawn");
        }
    }
}