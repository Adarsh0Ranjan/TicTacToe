package Stratgies;

import Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        // TODO: Add other levels as well
        return new EasyBotPlayingStrategy();
    }
}
