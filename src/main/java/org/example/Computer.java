package org.example;

import java.util.Random;

public class Computer {
    private GameField gameField;
    private Coord coord;
    private boolean isMove;


    public Computer() {
        coord = new Coord();
        gameField = GameField.start();
    }

    public void moveComputer() {
        coord.setX(getRandomCoord());
        coord.setY(getRandomCoord());
        if (checkIsMove()) {
            if (gameField.getField()[coord.getY()][coord.getX()] == GameField.NULL) {
                gameField.getField()[coord.getY()][coord.getX()] = GameField.ZERO;
            } else {
                moveComputer();
            }
        }
    }

    private int getRandomCoord() {
        Random random = new Random();
        int var = random.nextInt(3);
        return var;
    }

    private boolean checkIsMove() {
        isMove = gameField.checkIsMove();
        return isMove;
    }

}
