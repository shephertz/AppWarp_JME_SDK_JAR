package app;

import javax.microedition.lcdui.Canvas;

public class Constants {
	public static final int UP_KEY=-1;
	public static final int DOWN_KEY=-2;
	public static final int LEFT_KEY=-3;
	public static final int RIGHT_KEY=-4;
	public static final int OK_KEY=-5;
	public static final int LSK_KEY=-6;
	public static final int RSK_KEY=-7;
	public static final int TWO_KEY=50;
	public static final int FOUR_KEY=52;
	public static final int FIVE_KEY=53;
	public static final int SIX_KEY=54;
	public static final int EIGHT_KEY=56;
	public static final int KEY_NUM0=Canvas.KEY_NUM0;
	public static int MY_ZAPPER_POINTS=200;
	
	public static String getRandomUserName(){
		String str = System.currentTimeMillis()+"";
		str = str.substring(str.length()/2+1,str.length());
		return str;
	}
}
