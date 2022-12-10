package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame {

    private JPanel panel;
    private Computer computer;
    Player player;
    GameField gameField;


    private final int IMAGE_SIZE = 200;

    public Window() {
        initRes();
        initPanel();
        mouseClick();
        initFrame();
    }

    private void initFrame() {
        pack();
        setTitle("Крестики Нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D) g;
                BasicStroke pen = new BasicStroke(2);
                graphics2D.setStroke(pen);
                graphics2D.drawLine(IMAGE_SIZE, 0, IMAGE_SIZE, IMAGE_SIZE * SizeField.LENGTH);
                graphics2D.drawLine(IMAGE_SIZE * (SizeField.LENGTH - 1), 0, IMAGE_SIZE * (SizeField.LENGTH - 1), IMAGE_SIZE * SizeField.LENGTH);
                graphics2D.drawLine(0, IMAGE_SIZE, IMAGE_SIZE * SizeField.HEIGTH, IMAGE_SIZE);
                graphics2D.drawLine(0, IMAGE_SIZE * (SizeField.HEIGTH - 1), IMAGE_SIZE * SizeField.HEIGTH, IMAGE_SIZE * (SizeField.HEIGTH - 1));
                for (int y = 0; y < gameField.getField().length; y++) {
                    for (int x = 0; x < gameField.getField()[y].length; x++) {
                        if (gameField.getField()[y][x] == 1) {
                            graphics2D.setColor(Color.GREEN);
                            g.drawLine(x * IMAGE_SIZE, y * IMAGE_SIZE, (x + 1) * IMAGE_SIZE, (y + 1) * IMAGE_SIZE);
                            g.drawLine((x + 1) * IMAGE_SIZE, y * IMAGE_SIZE, x * IMAGE_SIZE, (y + 1) * IMAGE_SIZE);
                        } else {
                            if (gameField.getField()[y][x] == -1) {
                                graphics2D.setColor(Color.RED);
                                g.drawOval(x * IMAGE_SIZE, y * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
                            }
                        }
                    }
                }
            }
        };
        panel.setPreferredSize(new Dimension(SizeField.HEIGTH * IMAGE_SIZE, SizeField.LENGTH * IMAGE_SIZE));
        add(panel);
    }

    private void initRes() {
        computer = new Computer();
        player = new Player();
        gameField = GameField.start();
    }
    private void mouseClick() {
        panel.addMouseListener(new MouseListener() {

            //            Вызывается при нажатии копи мыши, только не менять положение курсора мыши
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            //            Вызвается при нажатии кнопкой мыши на компоненте
            @Override
            public void mousePressed(MouseEvent e) {
                player.setCoord(e.getX() / IMAGE_SIZE, e.getY() / IMAGE_SIZE);
            }

            //            Вызывается после нажатия кнопки мыши и ее отпускания
            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameField.getField()[e.getY() / IMAGE_SIZE][e.getX() / IMAGE_SIZE] == GameField.NULL) {
                    player.movePlayer();
                    computer.moveComputer();
                }
                repaint();
                int win = gameField.checkForWin();
                if (win == GameField.CROSS || win == GameField.ZERO) {
                    String winStr = "Игра закончена! Выйграли " + (win == GameField.CROSS ? " КРЕСТИКИ" : " НОЛИКИ");
                    JOptionPane.showMessageDialog(null,winStr);
                    gameField.restart();
                    repaint();
                }
            }

            //            Вызывается когда курсор находится на компоненте
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            //            Вызывается после mouseEntered когда убрали с компонента
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}

