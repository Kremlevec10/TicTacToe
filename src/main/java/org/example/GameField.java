package org.example;

import java.util.Arrays;

public class GameField {

    public static final int CROSS = 1;
    public static final int ZERO = -1;
    public static final int NULL = 0;
    private static GameField gameField = null;
    private int[][] field;

    public GameField() {
        initField();
    }

    public static GameField start() {
        if (gameField == null) {
            gameField = new GameField();
        }
        return gameField;
    }

    private void initField() {
        field = new int[SizeField.HEIGTH][SizeField.LENGTH];
    }

    public void printField() {
        for (int i = 0; i < field.length; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
    }

    public boolean checkIsMove() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkForWin() {
        int chekLine = chekLine(), checkDiaganal = checkDiaganal(), checkVertical = chekVertical();
        if (chekLine > 0 || checkDiaganal > 0 || checkVertical > 0) {
            return CROSS;
        } else if (chekLine < 0 || checkDiaganal < 0 || checkVertical < 0) {
            return ZERO;
        }
        return 0;
    }

    private int chekLine() {
        for (int j = 0; j < field.length; j++) {
            boolean checkCross = true, checkZero = true;
            for (int i = 0; i < field[j].length; i++) {
                checkCross = checkCross && field[j][i] == CROSS;
                checkZero = checkZero && field[j][i] == ZERO;
            }
            if (checkCross) {
                return CROSS;
            } else if (checkZero) {
                return ZERO;
            }
        }
        return 0;
    }

    private int chekVertical() {
        for (int j = 0; j < field.length; j++) {
            boolean checkCross = true, checkZero = true;
            for (int i = 0; i < field[j].length; i++) {
                checkCross = checkCross && field[i][j] == CROSS;
                checkZero = checkZero && field[i][j] == ZERO;
            }
            if (checkCross) {
                return CROSS;
            } else if (checkZero) {
                return ZERO;
            }
        }
        return 0;
    }

    private int checkDiaganal() {
        boolean checkCross = true, checkZero = true, checkAnotherCross = true, checkAnotherZero = true;
        for (int i = 0; i < field.length; i++) {
            checkCross = checkCross && field[i][i] == CROSS;
            checkAnotherCross = checkAnotherCross && field[i][(field.length - 1) - i] == CROSS;
            checkZero = checkZero && field[i][i] == ZERO;
            checkAnotherZero = checkAnotherZero && field[i][(field.length - 1) - i] == ZERO;
        }
        if (checkCross || checkAnotherCross) {
            return CROSS;
        } else if (checkZero || checkAnotherZero) {
            return ZERO;
        }
        return 0;
    }

    public void restart() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = 0;
            }
        }
    }

    public int[][] getField() {
        return field;
    }
}
