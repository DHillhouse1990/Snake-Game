
package snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game 
implements KeyListener{
	private Snake player;
	private Food food;
	private Graphics graphics;
	
	private static JFrame window;
        private static boolean setUp = false;
	
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	
	public Game() {
           
            if(window == null){
		window = new JFrame();
            }
		player = new Snake();
		
		food = new Food(player);
		
		
                

                graphics = new Graphics(this);
		window.add(graphics);
            if(!setUp){
	
		window.setTitle("Snake");
		window.setSize(width * dimension + 2, height * dimension + dimension + 4);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setUp = true;
            }    
	}
	
	public void start() {
		graphics.state = "RUNNING";
	}
	
	public void update() {
		if("RUNNING".equals(graphics.state)) {
			if(check_food_collision()) {
				player.grow();
				food.random_spawn(player);
			}
			else if(check_wall_collision()) {
				transferThroughWall();
			} else if(check_self_collision()){
                            graphics.state = "END";
                        }
			else {
				player.move();
			}
		}
	}
        
        private void transferThroughWall(){
            
            String move = player.getMove();
            Rectangle first = player.getBody().get(0);

            Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

            if(player.getY() <= 0 && "UP".equals(move)) {
                System.out.println("we have crashed");
                    temp.setLocation(first.x, height * dimension - dimension);
            }
            else if(player.getY() >= height * dimension && move == "DOWN") {
                    temp.setLocation(first.x, 0 + dimension);
            }
            else if(player.getX() < 0 && "LEFT".equals(move)) {
                    temp.setLocation(width * dimension + 2 - Game.dimension, first.y);
            }
            else if(player.getX() >= width * dimension && "RIGHT".equals(move))
                    temp.setLocation(0 + dimension, first.y);
            

            player.getBody().add(0, temp);
            player.getBody().remove(player.getBody().size()-1);
        
        }
      
	private boolean check_wall_collision() {
               
		if(player.getX() < 0 || player.getX() >= width * dimension 
				|| player.getY() < 0|| player.getY() >= height * dimension) {
			return true;
                        
		}
		return false;
	}
	
	private boolean check_food_collision() {
		if(player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension) {
			return true;
		}
		return false;
	}
	
	private boolean check_self_collision() {
		for(int i = 1; i < player.getBody().size(); i++) {
			if(player.getX() == player.getBody().get(i).x &&
					player.getY() == player.getBody().get(i).y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(graphics.state == "RUNNING") {
			if(keyCode == KeyEvent.VK_W || keyCode == 38 && player.getMove() != "DOWN") {
				player.up();
			}
		
			if(keyCode == KeyEvent.VK_S || keyCode == 40 && player.getMove() != "UP") {
				player.down();
			}
		
			if(keyCode == KeyEvent.VK_A || keyCode == 37 && player.getMove() != "RIGHT") {
				player.left();
			}
		
			if(keyCode == KeyEvent.VK_D || keyCode == 39 && player.getMove() != "LEFT") {
				player.right();
			}
		}
		else {
			this.start();
		}
	}
       

	@Override
	public void keyReleased(KeyEvent e) {	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
    
    
    
    
    
}
