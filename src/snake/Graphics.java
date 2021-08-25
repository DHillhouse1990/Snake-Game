

package snake;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.Timer;

public final class Graphics
extends JPanel
implements ActionListener{
	private Timer timer = new Timer(100, this);
	public String state;
	
	private Snake snake;
	private Food food;
	private static Game game;
        private int highScore;
        
	
	public Graphics(Game g) {
		setUpGraphics(g);
		
		//add a keyListner 
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
        
        public void setUpGraphics(Game g){
            timer.start();
            state = "START";

            game = g;
            snake = g.getPlayer();
            food = g.getFood();
        }
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);
		
		if("START".equals(state)) {
			g2d.setColor(Color.white);
			g2d.drawString("Press Any Key", Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
		}
		else if("RUNNING".equals(state)) {
			g2d.setColor(Color.red);
			g2d.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension);
		
			g2d.setColor(Color.green);
			for(Rectangle r : snake.getBody()) {
				g2d.fill(r);
			}
		}
                else if(state.equals("END")){
			g2d.setColor(Color.white);
                        int score = snake.getBody().size() - 3;
                        if(highScore < score){
                            highScore = score;   
                        }
			g2d.drawString("Your Score: " + score, Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
                        g2d.drawString("High Score: " + highScore, Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 5);                                                            
		}
	}
        //hit button ro restart
        
       
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
}

