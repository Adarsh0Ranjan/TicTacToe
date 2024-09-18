package Models;

import Stratgies.BotPlayingStrategy;
import Stratgies.BotPlayingStrategyFactory;

public class Bot extends  Player {

    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;


    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, id, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }



    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
