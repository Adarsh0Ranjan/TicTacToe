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

    public void makeMove() {
        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + " 's move. Please make your move");

        // move made by player
        Move move = currentPlayer.makeMove(this.board);

        System.out.println(currentPlayer.getName() + " has made the move at row: " + move.getCell().getRow() + " and col: " + move.getCell().getCol());
        // validate the move
        if(!validate(move)) {
            System.out.println("Invalid move. Please try again");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        // update the cell
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setPlayer(currentPlayer);

        // validated move
        Move finalMove = new Move(currentPlayer, cellToChange);

        // add validated move to move list
        this.moves.add(finalMove);

        // update next turn, taking mod so that we can get the first player after reaching the last player
        nextMovePlayerIndex = (this.nextMovePlayerIndex + 1) % players.size();

        // check if there is a winner

        if (checkWinner(finalMove, board)) {
            winner = currentPlayer;
            gameState = GameState.WIN;
        } else if(moves.size() == board.getSize() * board.getSize()) {
            gameState = GameState.DRAW;
        }

    }

    // TODO: think about moving this to other class
    private boolean validate(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()) {
            return  false;
        }

        if(col >= board.getSize()) {
            return false;
        }

        if(this.gameState != GameState.IN_PROGRESS) {
            return false;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return true;
        }

        return false;
    }

    public boolean checkWinner(Move move, Board board) {
        for(WinningStrategy winningStrategy: winningStrategies) {
            if(winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }
        return false;
    }

    public void undo() {
        if(moves.size() == 0) {
            System.out.println("No Moves To Do");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(lastMove);

        // update the cell
        Cell cellToChange = lastMove.getCell();
        cellToChange.setCellState(CellState.EMPTY);
        cellToChange.setPlayer(null);
        for(WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(lastMove, board);
        }

        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + players.size()) % players.size();



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
