
import java.util.logging.Level;

import java.util.*;
import java.util.logging.Logger;
import org.newdawn.slick.fills.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.*;

public class BreakoutGame implements Game{
	
	private final String title;
	private Player player;
	private Ball ball;
	private MyLevel levelOne;
	private ArrayList<Brick> currentBrickList;
	private boolean debug = true;
	private Image playerImg, ballImg, firstBrickImg;
	
	public BreakoutGame(String gamename){
		title = gamename;	
	}
 
	public void init(GameContainer gc) throws SlickException {
		gc.setTargetFrameRate(120);
		gc.setMaximumLogicUpdateInterval(10);
		player = new Player(gc.getWidth()/2, gc.getHeight() - 45, 200, 40);
		ball = new Ball(20, gc.getHeight()/2.0f);
		levelOne = new MyLevel(gc.getWidth(), gc.getHeight());
		currentBrickList = levelOne.getBrickList();
		playerImg = new Image("Player.png");
		ballImg = new Image("Ball.png");
		firstBrickImg = new Image("FirstBrick.png");
	}

	public void update(GameContainer gc, int i) throws SlickException {
		Input in = gc.getInput();
		float speed = player.getSpeed();
		float s = 0;
		Iterator<Brick> iter = currentBrickList.iterator();
		//TODO Figure out if this i is necessary
		if(in.isKeyDown(Input.KEY_LEFT))
			s = -speed * i;
		if(in.isKeyDown(Input.KEY_RIGHT))
			s = speed * i;
		if(in.isKeyDown(Input.KEY_ESCAPE))
			gc.exit();
		if(in.isKeyDown(Input.KEY_F1))
			debug = (debug) ? false : true;
			
		float newX = player.getX() + s;
		
		if(newX > 0 && newX + player.getWidth() < gc.getWidth())
			player.setX((int)newX);
		
		while(iter.hasNext()){
			if(ball.shape.intersects(iter.next().getShape())){
				iter.remove();
				if(currentBrickList.isEmpty()){
					gc.pause();
				}
			}
		}
		
		ball.move(ball.shape.intersects(player.shape), i, player.getShape());
		
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		
		g.drawImage(playerImg, player.getX(), player.getY());
		//g.drawImage(ballImg, ball.getX(), ball.getY());
		g.texture(ball.shape, ballImg, true);
		
		for(Brick b : currentBrickList){
			if(b.getType() == Brick.type.FIRST){
//				g.drawImage(firstBrickImg, b.getX(), b.getY());
				g.texture(b.shape, firstBrickImg, true);
			}
		}
		
		if(debug){
			g.drawString("Ball speed: " + ball.getSpeed(), gc.getWidth() - 200, 20);
			g.drawString("FPS: " + gc.getFPS(), 20, 20);
			g.drawString("Current XDir: " + ball.getXMagnitude(), 20, 40);
			g.drawString("Current YDir: " + ball.getYMagnitude(), 20, 60);
			//TODO Drawing a string breaks the FPS counter. Why?
		}
		if(ball.isLost()){
			g.drawString("You lose.", gc.getWidth() / 2 - 20, gc.getHeight() / 2);
			ball.setSpeed(0);
		}else if(gc.isPaused()){
			g.drawString("You win!", gc.getWidth() / 2 - 20, gc.getHeight() / 2);
			ball.setSpeed(0);
		}
		
	}

	public static void main(String[] args){
		try
		{
			AppGameContainer appgc;
			BreakoutGame breakout = new BreakoutGame("Breakout");
			ScalableGame breaks = new ScalableGame(breakout, 1920, 1080, false);
			appgc = new AppGameContainer(breaks);
			
			appgc.setDisplayMode(1920, 1080, true);
			appgc.setMouseGrabbed(true);
			appgc.setShowFPS(false);
			
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(BreakoutGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public boolean closeRequested() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle() {
		return title;
	}
}