import org.newdawn.slick.geom.Shape;

public abstract class Entity {
	
	protected Shape shape;
	protected float speed;
	protected int screenWidth, screenHeight;
	
	public Shape getShape(){
		return shape;
	}
	
	public void setX(float x){
		shape.setX(x);
	}
	
	public void setY(float y){
		shape.setY(y);
	}
	
	public void setSpeed(float s){
		speed = s;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public float getX(){
		return shape.getX();
	}
	
	public float getY(){
		return shape.getY();
	}
	
	public float getHeight(){
		return shape.getHeight();
	}
	
	public float getWidth(){
		return shape.getWidth();
	}
	
	
}
