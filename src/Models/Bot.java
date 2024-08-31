package Models;

import Stratgies.BotPlayingStrategy;

public class Bot extends  Player {

    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;


    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, id, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

}
