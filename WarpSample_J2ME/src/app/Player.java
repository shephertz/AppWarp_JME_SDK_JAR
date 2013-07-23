package app;

public class Player {
	
	private String name;
	private int colorIndex;
	private int XX;
	private int YY;
	private final int steps = 5;
	
	public Player(String name, int colorIndex, int xx, int yy) {
		this.name = name;
		this.colorIndex = colorIndex;
		this.XX = xx;
		this.YY = yy;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIndex(){
		return colorIndex;
	}
	
	public int getXX(){
		return XX;
	}
	
	public int getYY(){
		return YY;
	}
	
	public void move(int dir){
		switch (dir) {
			case 1:
				XX+=steps;
			break;
			case 2:
				XX-=steps;
			break;
			case 3:
				YY-=steps;
			break;
			case 4:
				YY+=steps;
			break;
		}
	}
}
