package View;

import Controller.MainClass;
import Controller.PacmanController;
import Model.Game;
import Model.Maze;
import Model.Pacman;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The view of the game view
 */
public class GameView extends Canvas{
	private	static JFrame frame;
	private	static Game game;
	private	static	ViewLogic instance;
	private static PacmanController controller;
	private int x;
	private int y;

	public GameView()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameView(int x, int y){
	    this.x = x;
	    this.y = y;
    }

    public static void  initGame(){

        JPanel panel=new JPanel();
        panel.setMaximumSize(new Dimension(200,200));
        panel.setMinimumSize(new Dimension(200,200));
        panel.setBackground(Color.BLACK);
        BorderLayout layout = new BorderLayout();
        layout.setHgap(15);
        layout.setVgap(15);
        panel.setLayout(layout);

        JLabel backBTN = new JLabel("___________________________");
    //    backBTN.setForeground(Color.white);
        backBTN.setHorizontalTextPosition(JLabel.CENTER);
        backBTN.setVerticalTextPosition(JLabel.BOTTOM);
        backBTN.setOpaque(true);
        backBTN.setBackground(Color.BLACK);
        Image back = null;
        Image newImage = null;
        try {
            back = ImageIO.read(GameView.class.getResource("/res/BackBTN.PNG"));
             newImage = back.getScaledInstance(300, 50, Image.SCALE_DEFAULT);
            backBTN.setIcon(new ImageIcon(newImage));
        } catch (IOException e) {
            System.out.println("does not succeed");
            e.printStackTrace();
        }
        backBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if( PopUpLogic.getInstance().ShowEXit(false)==0) {
                    closewindow();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                backBTN.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backBTN.setForeground(Color.black);
            }
        });
        backBTN.setMaximumSize(new Dimension(100,100));
        backBTN.setMinimumSize(new Dimension(100,100));
                game = Game.getInstance();
        instance = ViewLogic.getInstance();
         frame = new JFrame(game.TITLE);
        frame.add(BorderLayout.CENTER, game);

        panel.add(instance.getNickname(),BorderLayout.NORTH);
        panel.add(instance.getlScoreForPacman(),BorderLayout.LINE_START);
        panel.add(instance.getLifeScoreForPacman(),BorderLayout.LINE_END);
        instance.getTimer();

        panel.add(BorderLayout.CENTER,instance.timer);
        frame.add(BorderLayout.NORTH, panel);
        frame.add(BorderLayout.SOUTH,backBTN);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
       	ImageIcon img = new ImageIcon("res/pacnanIm.png");
       	frame.setIconImage(img.getImage());
         game.start();

      frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    	public void windowClosing(java.awt.event.WindowEvent e) {
	       if( PopUpLogic.getInstance().ShowEXit(false)==0) {
		        closewindow();
	       }
	 	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    	}
        });
       // StartGame.closeMain();
    }
 /***update all the instance to null
  * in favor to open the game again
  *
  */


	public static void closewindow() {

	 	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispose();
		game.isRunning=false;
  		game.SetInstance();
 		instance.SetInstance();
 	}

    /**
     * Responsible to render the game's board
     */
    public void render(ArrayList<Pacman> pacmans, ArrayList<Maze> mazes, int playerIndex, BufferStrategy bs) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        pacmans.get(playerIndex).render(g);
        mazes.get(playerIndex).render(g);
        g.dispose();
        bs.show();
    }

}
