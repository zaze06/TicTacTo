package me.alien.tic.tac.to;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JPanel implements ActionListener, MouseListener {

    private Timer timer;
    private Vector2 mousePosition = new Vector2(-1, -1);

    int[][] map = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };
    int turn = 1;

    Box[][] cels;

    boolean win = false;
    int winer;

    public Game (int Delay){
        initTimer(Delay);
    }

    private void initTimer(int Delay) {
        timer = new Timer(Delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        int h = getHeight();
        int w = getWidth();

        if(cels == null){
            cels = new Box[][]{
                    {new Box(new Vector2(0,0), new Vector2(w/3, h/3)), new Box(new Vector2(w/3, 0), new Vector2(w-(w/3),h/3)), new Box(new Vector2(w-(w/3),0), new Vector2(w, h/3))},
                    {new Box(new Vector2(0,h/3), new Vector2(w/3, h-(h/3))), new Box(new Vector2(w/3, h/3), new Vector2(w-(w/3),h-(h/3))), new Box(new Vector2(w-(w/3),h/3), new Vector2(w, h-(h/3)))},
                    {new Box(new Vector2(0,h-(h/3)), new Vector2(w/3, h)), new Box(new Vector2(w/3, h-(h/3)), new Vector2(w-(w/3),h)), new Box(new Vector2(w-(w/3),h-(h/3)), new Vector2(w, h))}
            };
        }

        Graphics2D g2d = (Graphics2D) g;

        Vector2 point = new Vector2(0,h/3);

        g2d.drawLine(point.getX(), point.getY(), w, point.getY());

        point.setY(h-(h/3));

        g2d.drawLine(point.getX(), point.getY(), w, point.getY());

        point = new Vector2(w/3,0);

        g2d.drawLine(point.getX(), point.getY(), point.getX(), h);

        point.setX(w-(w/3));

        g2d.drawLine(point.getX(), point.getY(), point.getX(), h);

        point = new Vector2(0, 0);

        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                Box cel = cels[x][y];
                if(cel.contains(mousePosition)){
                    System.out.println("mouse relese pos ("+mousePosition.getX()+","+mousePosition.getY()+") inside of cel ("+x+","+y+")");
                    if(!win) {
                        if (map[x][y] == 0) {
                            map[x][y] = turn;
                            //if (turn == 1) turn = 2;
                            //else if (turn == 2) turn = 1;
                        }
                    }
                }
                if(map[x][y] == 1){
                    g2d.drawLine(cel.getStart().getX(), cel.getStart().getY(), cel.getEnd().getX(), cel.getEnd().getY());
                    g2d.drawLine(cel.getStart().getX(), cel.getEnd().getY(), cel.getEnd().getX(), cel.getStart().getY());
                }else if(map[x][y] == 2){
                    g2d.drawOval(cel.getStart().getX()+5, cel.getStart().getY()+5, cel.getW()-10, cel.getH()-10);
                }
            }
        }

        doWinChenk(g2d);
        if(win){
            g2d.drawString(winer+" Won", cels[1][1].getStart().getX(), cels[1][1].getH()/2);
        }
    }

    private void doWinChenk(Graphics2D g2d) {
        if(detect(1, cels, g2d)){
            win = true;
            return;
        }
        if(detect(2, cels, g2d)){
            win = true;
            return;
        }
    }

    private boolean detect(int p, Box[][] cels, Graphics2D g2d) {
        g2d.setColor(Color.red);
        if(map[0][0] == p && map[1][0] == p && map[2][0] == p){
            g2d.drawLine(cels[0][0].getW()/2, cels[0][0].getStart().getY(), cels[2][0].getW()/2, cels[2][0].getEnd().getY());
            winer = p;
            return true;
        }else if(map[0][1] == p && map[1][1] == p && map[2][1] == p){
            g2d.drawLine(cels[1][0].getW()/2, cels[1][0].getStart().getY(), cels[1][2].getW()/2, cels[1][2].getEnd().getY());
            winer = p;
            return true;
        }else if(map[0][2] == p && map[1][2] == p && map[2][2] == p){
            g2d.drawLine(cels[0][2].getW()/2, cels[0][2].getStart().getY(), cels[2][2].getW()/2, cels[2][2].getEnd().getY());
            winer = p;
            return true;
        }else if(map[0][0] == p && map[0][1] == p && map[0][2] == p){
            g2d.drawLine(cels[0][0].getStart().getX(), cels[0][0].getH()/2, cels[0][2].getEnd().getX(), cels[0][2].getH()/2);
            winer = p;
            return true;
        }else if(map[1][0] == p && map[1][1] == p && map[1][2] == p){
            g2d.drawLine(cels[1][0].getStart().getX(), cels[1][0].getH()/2, cels[1][2].getEnd().getX(), cels[1][2].getH()/2);
            winer = p;
            return true;
        }else if(map[2][0] == p && map[2][1] == p && map[2][2] == p){
            g2d.drawLine(cels[2][0].getStart().getX(), cels[2][0].getH()/2, cels[2][2].getEnd().getX(), cels[2][2].getH()/2);
            winer = p;
            return true;
        }else if(map[0][0] == p && map[1][1] == p && map[2][2] == p){
            g2d.drawLine(cels[0][0].getStart().getX(), cels[0][0].getStart().getY(), cels[2][2].getEnd().getX(), cels[2][2].getEnd().getY());
            winer = p;
            return true;
        }else if(map[0][2] == p && map[1][1] == p && map[2][0] == p){
            g2d.drawLine(cels[0][2].getEnd().getX(), cels[0][2].getStart().getY(), cels[2][0].getStart().getX(), cels[2][0].getEnd().getY());
            winer = p;
            return true;
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON2){
            System.out.println("Button 2");
            if(win){
                win = false;
                winer = 0;
                map = new int[][]{
                        {0,0,0},
                        {0,0,0},
                        {0,0,0}
                };
                return;
            }
        }
        mousePosition = new Vector2(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePosition = new Vector2(-1,-1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
