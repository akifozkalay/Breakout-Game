/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram  {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
	private static final double GRAVITY = 3;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Animation delay or pause time between ball moves */ 
	private static final int DELAY = 10; 
	
	/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setupBricks();
		paddle();
		
		//For loop for player turns
		for(int i=0; i<NTURNS; i++){
			ballSetup();
			displayMessage();
		
			while (true ) {
				moveBall();
		//determines if game is over -- true if either if ball has moved to bottom of screen
		//or all Bricks are removed 
				if(ball.getY()>getHeight()) break;
				if(brickCount==totalBricks){
					win();
					break; 	 
				}
				checkForCollision();
				pause(DELAY); 
		
			}
				if(brickCount==totalBricks){
					win();
					break;
				} 
	}gameover();
 }

	
		

    //setup brick 10X10 and fill color depending on the row
	private void setupBricks(){
		
		/*need to have several columns in each row
		 * so there need to be two for loops, 
		 * one for loop for the rows and one for loop for the columns.
		 */ 
		for(int row=0; row<NBRICK_ROWS; row++){
			for(int col=0; col<NBRICKS_PER_ROW; col++){
				rect = new	GRect(BRICK_WIDTH,BRICK_HEIGHT );
				add(rect,((col* (BRICK_WIDTH+BRICK_SEP ))), (BRICK_Y_OFFSET)+row*(BRICK_HEIGHT+BRICK_SEP ));	
			
				if(row<2){
					rect.setFillColor(Color.RED);
					rect.setFilled(true);
				}
				else if(row==2||row==3){
					rect.setFillColor(Color.ORANGE);
					rect.setFilled(true);
				}
				else if(row==4||row==5){
					rect.setFillColor(Color.YELLOW);
					rect.setFilled(true);
				}
				else if(row==6||row==7){
					rect.setFillColor(Color.GREEN);
					rect.setFilled(true);
				}
				else if (row==8||row==9){
					rect.setFillColor(Color.CYAN);
					rect.setFilled(true);
				}
			}
			
		}
		
	}
		
	//Create and place Paddle and add mouse listeners.
	private void paddle(){
		 paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
		add(paddle,(getWidth()/2),(getHeight()-PADDLE_Y_OFFSET));	
		paddle.setFilled(true);
		addMouseListeners();
		 
}
	public void mouseMoved(MouseEvent e) {
		
		if(e.getX()<=(getWidth()-PADDLE_WIDTH))
		paddle.setLocation(e.getX(), (getHeight()-PADDLE_Y_OFFSET));
	}
	
	//Create and place ball.
	private void ballSetup(){
		
		ball = new GOval(APPLICATION_HEIGHT/2,APPLICATION_WIDTH/2,2*BALL_RADIUS,2*BALL_RADIUS);
		add(ball);
		ball.setFilled(true);
		
	
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
	
		vy=0.0;
		vy+=GRAVITY;
		
		  
		
		 
}
		
		 //Update and move ball
		//Determine if collision with floor, update velocities 
	  //and location as appropriate. 
	private void moveBall(){
		
		ball.move(vx,vy);
		if(ball.getY()<BALL_RADIUS){
			vy=-vy;
			 
		}
		//check if ball hit the sides of the wall
		if(ball.getX()<BALL_RADIUS ||ball.getX()>getWidth()-BALL_RADIUS*2){
			vx=-vx;
			 
		}
		
		 
	}
           //Determine if collision with paddle or bricks
	private void checkForCollision() {
		
		
		collider = getCollidingObject();
		if ( collider != null ){
			if ( collider == paddle ) {
				bounceClip.play();
				vy = -vy;
				
			}
			else{
				bounceClip.play();
				remove(collider);
				brickCount++;
				vy = -vy;
			}
		}
		
	}
		

	 
		
		//get object at collision 

	private GObject getCollidingObject() {
		 
		double x = ball.getX();
		double y = ball.getY();
		   
		if ( getElementAt(x, y)!= null ) {
			return getElementAt(x, y);
		}
		else if(getElementAt(x+2*BALL_RADIUS, y)!=null){
			return getElementAt(x+2*BALL_RADIUS, y  );
		}
		else if(getElementAt(x,y+2*BALL_RADIUS)!=null){
			return getElementAt(x,y+2*BALL_RADIUS);
		}		
		else if(getElementAt(x+2*BALL_RADIUS,y+2*BALL_RADIUS)!=null){
			return getElementAt(x+2*BALL_RADIUS,y+2*BALL_RADIUS);
		}	

		return null;
 }
	//display start message
	private void displayMessage(){
		GLabel mess = new GLabel("Click Mouse Button", 50,getHeight()/2);
		mess.setFont(new Font("Serif", Font.BOLD, 18));
		mess.setColor(Color.blue);
		add(mess);
		waitForClick();
		remove(mess);
	}
			
	//Display if user win.
	private void win(){
		GLabel win = new GLabel("Wow You Win", getWidth()/3,getHeight()/2);
		win.setFont(new Font("Serif", Font.BOLD, 18));
		win.setColor(Color.blue);
		add(win);
		
	}
	 
	private void gameover(){
		GLabel lose = new GLabel("Game Over", getWidth()/3,getHeight()/2);
		lose.setFont(new Font("Serif", Font.BOLD, 18));
		lose.setColor(Color.blue);
		add(lose);
		
	}	
	AudioClip bounceClip = MediaTools.loadAudioClip("sfx.au");
	private int brickCount;
	private int totalBricks=100;
	 
	private GRect paddle;
	private GObject collider ;
	
	private GRect rect;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx;
	private double vy;
	
	
	
}