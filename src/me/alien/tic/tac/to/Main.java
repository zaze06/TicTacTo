package me.alien.tic.tac.to;

import me.alien.tic.tac.to.multiplayer.Host;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;

public class Main extends JFrame {
    static int hight = 268;
    static int width = 290;
    static final int DELAY = 40;

    public Main(String[] args){initUI(args);}

    public void initUI(String[] args) {
        int length = args.length;
        if (length >= 1){
            if (args[0].equals("-help")) {

            }
            if (args[0].equals("-mp")) {
                if (length >= 2){
                    if (args[1].equals("-host")){
                        int port = 3849;
                        if (length >= 4){
                            if(args[2].equals("-port")){
                                try{
                                    port = Integer.parseInt(args[3]);
                                }catch (NumberFormatException e){
                                    System.out.println("invalid port");
                                }
                            }
                        }

                        Host host = new Host(DELAY, port);
                        add(host);
                        addMouseListener(Host);
                    } else if (args[1].equals("-client")){
                        if (length >= 4){
                            if(args[2].equals("-ip")){
                                try {
                                    InetAddress address = InetAddress.getByName(args[3]);
                                }catch (java.net.UnknownHostException e){
                                    System.out.println("Invalid ip "+args[3]+"\nError log exist at \""+saveError(e)+"\"");
                                    System.exit(0);
                                }catch (SecurityException e){
                                    System.out.println("Security Exeption check firewall setings");
                                }
                            }
                        }else{

                        }
                    }

                } else{
                    System.out.println("Invalid argument use argument -help");
                }
            }
        }else{
            Game game = new Game(DELAY);
            add(game);
            addMouseListener(game);
        }


        setTitle("Tic Tac To");
        setSize(width, hight);
        setLocationRelativeTo(null);
        //setMaximumSize(getSize());
        //setMaximumSize(getSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private <T extends Exception> String saveError(T e){
        final Date date = new Date();
        File file = new File(System.getProperty("user.dir")+"/crash/"+ date.getDate() +"-"+date.getHours()+":"+date.getMinutes()+".txt");
        int i = 1;
        while(file.exists()){
            file = new File(System.getProperty("user.dir")+"/crash/"+ date.getDate() +"-"+date.getHours()+":"+date.getMinutes()+"  "+i+".txt");
            i++;
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
                return("cant create error file");
            }
        }

        return(file.getAbsolutePath());
    }

    public static void main(String[] args){
        EventQueue.invokeLater((new Runnable() {
            @Override
            public void run() {
                Main ex = new Main(args);
                ex.setVisible(true);
            }
        }));
    }
}
