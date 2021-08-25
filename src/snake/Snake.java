
package snake;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Rectangle> body;
	private int w = Game.width;
	private int h = Game.height;
	private int d = Game.dimension;
	
	public String move; //NOTHING, UP, DOWN, LEFT, RIGHT
	
	public Snake() {
                // storing ArrayList in the String move
		body = new ArrayList<>();
		//instantiate new Rectangle TEMP object with Game dimension from Game class (20 X 20)
                // set the LOCATION of TEMP Rectangle with the width / dimension / height from Game class
                // Add the TEMP Rectangle Object to the BODY ArrayList
		Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation(Game.width / 2 * Game.dimension, Game.height / 2 * Game.dimension);
		body.add(temp);
		//creating another part of the snakes body
                //adding this to the body ArrayList
		temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation((Game.width / 2 - 1) * Game.dimension, (Game.height / 2) * Game.dimension);
		body.add(temp);
		//creating the final part of the snake body
                //adding this to the body ArrayList
		temp = new Rectangle(Game.dimension, Game.dimension);
		temp.setLocation((Game.width / 2 - 2) * Game.dimension, (Game.height / 2) * d);
		body.add(temp);
		//Snake body ArrayList complete (3 parts to the body)
                //String move is equal to NOTHING
		move = "NOTHING";
	}
	
	public void move() {
            //if String MOVE does not equal NOTHING then get the first(0) index of the BODY ArrayList and store it in Rectangle first object
            //create new Rectangle TEMP using dimensions from the Game class (20 X 20)
            //IF String MOVE equals UP set X TO index 0 AND Y to index 0 minus Game dimension which is 1 moving the snake UP on the Y Axis
            //add the TEMP Rectangle to ArrayList BODY at the first index (0) 
            // Remove last index of ArrayList
		if(move != "NOTHING") {
			Rectangle first = body.get(0);
			
			Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
			
			if(move == "UP") {
				temp.setLocation(first.x, first.y - Game.dimension);
			}
			else if(move == "DOWN") {
				temp.setLocation(first.x, first.y + Game.dimension);
			}
			else if(move == "LEFT") {
				temp.setLocation(first.x - Game.dimension, first.y);
			}
			else{
				temp.setLocation(first.x + Game.dimension, first.y);
			}
			
			body.add(0, temp);
			body.remove(body.size()-1);
		}
	}
	
	public void grow() {
		Rectangle first = body.get(0);
		
		Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
		
		if(move == "UP") {
			temp.setLocation(first.x, first.y - Game.dimension);
		}
		else if(move == "DOWN") {
			temp.setLocation(first.x, first.y + Game.dimension);
		}
		else if(move == "LEFT") {
			temp.setLocation(first.x - Game.dimension, first.y);
		}
		else{
			temp.setLocation(first.x + Game.dimension, first.y);
		}
		
		body.add(0, temp);
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}
	

	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}
	
	public int getX() {
		return body.get(0).x;
	}
	
	public int getY() {
		return body.get(0).y ;
	}
	
	public String getMove() {
		return move;
	}
	
	public void up() {
		move = "UP";
	}
	public void down() {
		move = "DOWN";
	}
	public void left() {
		move = "LEFT";
	}
	public void right() {
		move = "RIGHT";
	}
}