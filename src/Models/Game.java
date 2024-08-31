package Models;

import Exceptions.MoreThanOneBotException;
import Exceptions.PlayerCountMismatchException;
import Stratgies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<WinningStrategy> winningStrategies, List<Player> players) {
        this.winningStrategies = winningStrategies;
        this.players = players;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayerIndex = 0;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private List<Player> players;

        private int dimension;

        private List<WinningStrategy> winningStrategies;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimenSion(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return  this;
        }

        // TODO: move validation logic in a separate
        private void validateBotCount() throws Exception {
             int botCount = 0;
             for(Player player: players) {
                 if (player.getPlayerType().equals(PlayerType.BOT)) {
                     botCount++;
                 }
             }

            if (botCount > 1) {
                throw new MoreThanOneBotException();
            }
        }

        private void validateUniqueSymbolForThePlayers() {

        }

        private void validatePlayerCount() throws  Exception {
            if(players.size() != dimension - 1) {
                throw new PlayerCountMismatchException();
            }
        }

        private void validate() throws Exception {
            validateBotCount();
            validatePlayerCount();
            validateUniqueSymbolForThePlayers();
        }

        public Game build() throws Exception {
            validate();
            return new Game(dimension, winningStrategies, players);
        }
    }
}
