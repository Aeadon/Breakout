import org.newdawn.slick.geom.Rectangle;


public class Brick extends Entity {
	
	private boolean visible;
	public static enum type{FIRST, SECOND, THIRD, FOURTH};
	private type myType;
	private int hitPoints;
	
	public Brick(float x, float y, float w, float h, int sh, int sw){
		shape = new Rectangle(x, y, w, h);
		speed = 0;
		screenHeight = sh;
		screenWidth = sw;
		visible = true;
		setType(type.FIRST);
		switch(myType){
		case FIRST:
			hitPoints = 1;
			break;
		default:
		}
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void hit(){
		hitPoints --;
	}
	
	public int getHP(){
		return hitPoints;
	}
	
	public void setVisibility(boolean t){
		visible = t;
	}
	
	public type getType(){
		return myType;
	}
	
	private void setType(type t){
		myType = t;
	}
	
	
}
