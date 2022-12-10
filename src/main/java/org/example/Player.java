package org.example;

public class Player {

    private GameField gameField;
    private Coord coord;

    public Player() {
        gameField = GameField.start();
        coord = new Coord();
    }

    public void movePlayer() {
        if (gameField.getField()[coord.getY()][coord.getX()] == GameField.NULL) {
            gameField.getField()[coord.getY()][coord.getX()] = GameField.CROSS;
        }
    }

    public void setCoord(int x, int y) {
        coord.setX(x);
        coord.setY(y);
    }

    public Coord getCoord() {
        return coord;
    }
}
