package Stratgies;

import Models.Board;
import Models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
