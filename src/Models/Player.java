package Models;

import java.util.Scanner;

public class Player {
    private String name;
    private Long id;

    public Player(String name, Long id, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Move makeMove(Board board) {
        System.out.println("Please enter the row where you want to make the move");
        int row = scanner.nextInt();

        System.out.println("Please enter the col where you want to make the move");
        int col = scanner.nextInt();

       return new Move(this, new Cell(row, col));
    }
}
