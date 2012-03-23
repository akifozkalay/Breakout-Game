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
	private static final int DELAY = 20; 

	/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setupBricks();
		paddle();
		ballSetup();
		while (gameOver() ) {
		 
			
		 
		moveBall();
		//getCollidingObject();
		//bottomWall = true;
		
		checkForPaddleCollision();
		//checkForPaddleCollision();
		//checkForBricksCollision();
		pause(DELAY); 
	//if	(ball.getY() >= getHeight() -BALL_RADIUS ) break;
	
		}
	}


	
		


	private void setupBricks(){
		
		int temp = 12;
		for (int i=0;i<NBRICK_ROWS;i++){
			
			for (int j=0; j<NBRICKS_PER_ROW ; j++){
			
				rect = new GRect(BRICK_WIDTH,BRICK_HEIGHT);
				add(rect,((j*(BRICK_WIDTH+BRICK_SEP))),(BRICK_Y_OFFSET+temp));		
				
				if (i==0||i==1){
					rect.setFillColor(Color.RED);
					rect.setFilled(true);
				}else if (i==2||i==3){
					rect.setFillColor(Color.ORANGE);
					rect.setFilled(true);
				}else if (i==4||i==5){
					rect.setFillColor(Color.YELLOW);
					rect.setFilled(true);
				}else if (i==6||i==7){
					rect.setFillColor(Color.GREEN);
					rect.setFilled(true);
				}else if (i==8||i==9){
					rect.setFillColor(Color.CYAN);
					rect.setFilled(true);
				}
				
			}
			temp= temp+12;
			
		}
		
		
			
	}
		
	
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
	
	private void ballSetup(){
		
		 ball = new GOval(APPLICATION_HEIGHT/2,APPLICATION_WIDTH/2,2*BALL_RADIUS,2*BALL_RADIUS);
		
		add(ball);
		ball.setFilled(true);

		 ball.move(vx,vy); 
		 vy+=GRAVITY;
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		
		 
}
		
		 
		
	private void moveBall(){
		
		
		
		 gameover = 0;
	 
	  if (ball.getY()<BALL_RADIUS||ball.getY() > ( getHeight() - BALL_RADIUS))   {
		   // change ball's Y velocity to now bounce upwards 
		 gameover++;
		 ballSetup();
	  }
		  //vy = -vy;
		  if (ball.getX()<BALL_RADIUS || ball.getX() > getWidth() - BALL_RADIUS)  
		  vx = -vx;
		  
		  ball.move(vx,vy);
		 
	  }
	 

	private void checkForPaddleCollision() {
		
		
		collider = getCollidingObject();
		if ( collider != null ){
			if ( collider == paddle ) {
				vy = -vy;
				
			}
			else{
				remove(collider);
				vy = -vy;
			}
		}
		
	}
		

	private boolean gameOver() {
		if    (gameover==NTURNS)   {
		 return false;
		}
		return true;
	}
	
		
		
		
		
		
		/*if (ball != null) {
			  collObj = getElementAt(ball.getX(), ball.getY());
			 //collObj = getCollidingObject();
				
			  collObj = getElementAt(ball.getX()+(2*BALL_RADIUS) , ball.getY());    				 
			  collObj = getElementAt(ball.getX(), ball.getY()+(2*BALL_RADIUS));
					 
			  collObj = getElementAt(ball.getX()+(2*BALL_RADIUS), ball.getY()+(2*BALL_RADIUS));
				
			 
		
			 } 
		if ( collObj == paddle) 
				 
		{ vy = -vy;
		
		}
		
	}
	*/



/*private void checkForBricksCollision() {
	// TODO Auto-generated method stub
	if(ball.getY()<(getHeight()-150))
	{
		if (ball != null) {
			  collObj2 = getElementAt(ball.getX(), ball.getY());
			 //collObj = getCollidingObject();
				
			  collObj2 = getElementAt(ball.getX()+(2*BALL_RADIUS) , ball.getY());    				 
			  collObj2 = getElementAt(ball.getX(), ball.getY()+(2*BALL_RADIUS));
					 
			  collObj2 = getElementAt(ball.getX()+(2*BALL_RADIUS), ball.getY()+(2*BALL_RADIUS));
				
			 
		
			 } 
	}
		if ( collObj2 !=null){
			remove(collObj2);
			vy = -vy;
		}

	}*/
	private GObject getCollidingObject() {
		GObject gobj;
		double x = ball.getX();
		double y = ball.getY();
		gobj =  getElementAt(x, y);
		if (gobj == null )      
			gobj =  getElementAt(x+2*BALL_RADIUS, y  );
		if (gobj == null )      
			gobj =  getElementAt(x,y+2*BALL_RADIUS);
		if (gobj == null )      
			gobj =  getElementAt(x+2*BALL_RADIUS,y+2*BALL_RADIUS);
	
		return gobj;
 }
	/*private void checkForWallCollision() { 
		 if (ball.getY() > getHeight() - BALL_RADIUS);  
		   vy=-vy;
		   ball.move(vx,vy);
	}
	*/
		
	
	
	/* Private instance variables */
	
	//private int i;
	private int gameover;
	private GRect paddle;
	private GObject collider ;
	private GObject collObj2;
	private GRect rect;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx;
	private double vy=3.0;
	
	
	
}