import org.newdawn.slick.geom.*;

public class Ball extends Entity{
	
	private float xDir = 1;
	private float yDir = 1;
	private float maxSpeed = 3;
	private float maxXDir = 4;
	private boolean isLost = false;
	
	public Ball(float x, float y){
		this(x, y, 15f, 15f, 1.5f, 1080, 1920);
	}
	
	public Ball(float x, float y, float a, float b, float s, int sh, int sw){
		shape = new Ellipse(x, y, a, b);
		speed = s;
		screenHeight = sh;
		screenWidth = sw;
	}
	/**
	 * Increments the ball's position by the x and y magnitudes if
	 * the ball is not in contact with anything. Otherwise it will
	 * change the direction and then increment.
	 * @param hits - if in contact with player
	 * @param lastUpdateTime - for speed purposes
	 */
	public void move(boolean hits, int lastUpdateTime, Shape player){
		if(hits){
			switch(Player.checkRegion(this, player)){
			case 1:	
				if(xDir > 0){
					xDir *= -1.2;
				}else{
					xDir *= 1.2;
				}
				speed *= 1.1;
				break;
			case 2: 
				if(xDir > 0){
					xDir *= -0.9;
				}else{
					xDir *= 0.9;
				}
				speed *= 1.1;
				break;
			case 3:	
				xDir *= 0.8;
				speed *= 0.8;
				break;
			case 4: 
				if(xDir > 0){
					xDir *= 0.9;
				}else{
					xDir *= -0.9;
				}
				speed *= 1.1;
				break;
			case 5: 
				if(xDir > 0){
					xDir *= 1.2;
				}else{
					xDir *= -1.2;
				}
				speed *= 1.1;
				break;
			}
			yDir *= -1;
		}else if(this.getX() + shape.getWidth() >= screenWidth ||
				this.getX() <= 0){
			xDir *= -1;	
		}else if(this.getY() + shape.getHeight() >= screenHeight){
			yDir *= -1;
			isLost = true;
		}else if(this.getY() <= 0){
			yDir *= -1;
		}
		if(xDir > maxXDir){
			xDir = maxXDir;
		}
		if(xDir > 0 && xDir < 1){
			xDir = 1;
		}
		if(xDir < 0 && xDir < -maxXDir){
			xDir = -maxXDir;
		}
		speed = (speed < 1) ? 1 : speed;
		speed = (speed > maxSpeed) ? maxSpeed : speed;
		incrementPosition();
		//TODO Figure out what to do with this lastUpdateTime
	}
	
	public boolean inBounds(){
		if(this.getX() > 0 && this.getY() > 0 && this.getX() + shape.getWidth() < screenWidth &&
				this.getY() + shape.getHeight() < screenHeight){
			return true;
		}
		return false;
	}
	private boolean inBounds(float x, float y){
		if(x >= 0 && x <= screenWidth && y >= 0 && y <= screenHeight){
			return true;
		}
		return false;
	}
	
	public float getXMagnitude(){
		return xDir;
	}
	
	public float getYMagnitude(){
		return yDir;
	}
	
	public boolean isLost(){
		return isLost;
	}
	
	private void incrementPosition(){
		float newX, newY;
		newX = this.getX() + xDir * speed;
		newY = this.getY() + yDir * speed;
		if(inBounds(newX, newY)){
			this.setX(newX);
			this.setY(newY);
		}else if(inBounds(newX, this.getY())){
			this.setX(newX);
			newY = (newY > screenHeight) ? screenHeight : 0;
			this.setY(newY);
		}else if(inBounds(this.getX(), newY)){
			this.setY(newY);
			newX = (newX > screenWidth) ? screenWidth : 0;
			this.setX(newX);
		}else{
			this.setX(screenWidth / 2);
			this.setY(screenHeight / 2);
		}
		//TODO This check needs to be done for the player and all bricks
	}
}