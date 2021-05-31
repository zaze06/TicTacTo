package me.alien.tic.tac.to;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    static int hight = 268;
    static int width = 290;
    static final int DELAY = 40;

    public Main(){initUI();}

    public void initUI(){
        Game game = new Game(DELAY);
        add(game);
        addMouseListener(game);

        setTitle("Tic Tac To");
        setSize(width, hight);
        setLocationRelativeTo(null);
        setMaximumSize(getSize());
        setMaximumSize(getSize());
    }

    public static void main(String[] args){
        EventQueue.invokeLater((new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        }));
    }
}
