import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
public class Player extends Entity{
	
	private static float regionOne, regionTwo, regionThree, regionFour;
	
	public Player(){
		this(0, 0, 20, 100);
		speed = 1.0f;
	}
	
	public Player(int x, int y, int width, int height){
		shape = new Rectangle(x, y, width, height);
		speed = 1.0f;
		regionOne = width / 5;
		regionTwo = regionOne * 2;
		regionThree = regionOne * 3;
		regionFour = regionOne * 4;
	}
	
	public static int checkRegion(Ball ball, Shape player){
		if(ball.getX() <= player.getX() + regionOne){
			return 1;
		}else if(ball.getX() <= player.getX() + regionTwo){
			return 2;
		}else if(ball.getX() <= player.getX() + regionThree){
			return 3;
		}else if(ball.getX() <= player.getX() + regionFour){
			return 4;
		}else{
			return 5;
		}
	}
}	