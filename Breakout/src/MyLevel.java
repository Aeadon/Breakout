import java.util.*;
public class MyLevel {
	
	int screenWidth, screenHeight, brickHeight, brickWidth;
	ArrayList<Brick> brickList;
	
	public MyLevel(int sw, int sh){
		screenWidth = sw;
		screenHeight = sh;
		brickHeight = sh/30;
		brickWidth = sw/21;
		brickList = new ArrayList<Brick>();
		createBrickList();
	}
	
	private void createBrickList(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 14; j++){
				brickList.add(new Brick(j*brickWidth + screenWidth / 6, i*brickHeight + screenHeight / 3, brickWidth,
						brickHeight, screenWidth, screenHeight));
			}
		}
	}
	
	public ArrayList<Brick> getBrickList(){
		return brickList;
	}
	
}
