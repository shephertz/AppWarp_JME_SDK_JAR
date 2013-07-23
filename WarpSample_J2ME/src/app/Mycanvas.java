package app;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.shephertz.app42.appwarp.client.WarpClient;
import com.shephertz.app42.json.JSONObject;



public class Mycanvas extends Canvas {

	private StartMidlet midlet;
	private WarpClient warpClient;
	private boolean GAME_START = false;
	private Image image[];
	public String monsterName;
	private int monsterIndex;
	private int XX;
	private int YY;
	private String roomId;
	private final int steps = 5;
	private Vector player = new Vector();
	
	
	public Mycanvas(StartMidlet midlet, WarpClient warpClient) {
		this.midlet = midlet;
		this.warpClient = warpClient;
		try {
			image = new Image[4];
			for(int i=0;i<4;i++){
				image[i] = Image.createImage("/monster"+(i+1)+".png");
			}
		} catch (Exception e) {
			System.out.println("Exception in image loading...");
		}
	}
	
	public void startGame(String name, int index){
		System.out.println("startGame------------");
		this.warpClient.joinRoomWithNUser(1);
		monsterName = name;
		monsterIndex = index;
		
	}
	
	protected void paint(Graphics g) {
		g.setColor(0XFFFFFF);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(!GAME_START){
			g.setColor(0);
			g.drawString("Welcome", 10, 10, 0);
			g.drawString("Connected to appwarp", 10, 20, 0);
			g.drawString("Please wait...", 10, getHeight()/2, 0);
		}else{
			g.setColor(0);
			g.drawString("Back", getWidth(), getHeight(), Graphics.BOTTOM|Graphics.RIGHT);
			g.drawString("Press dir key or tap on screen", 10, 10, 0);
			if(player.size()>0){
				for(int i=0;i<player.size();i++){
					Player p = (Player)player.elementAt(i);
					g.drawString(p.getName(),p.getXX(),p.getYY()-image[p.getIndex()].getHeight()/2,Graphics.BOTTOM|Graphics.LEFT);
					g.drawImage(image[p.getIndex()], p.getXX(), p.getYY(), Graphics.HCENTER|Graphics.VCENTER);
				}
			}
		}
		
	}
	
	public void keyPressed(int keyCode){
		if(keyCode == Constants.RIGHT_KEY){
			move(1);
		}else if(keyCode == Constants.LEFT_KEY){
			move(2);
		}else if(keyCode == Constants.UP_KEY){
			move(3);
		}else if(keyCode == Constants.DOWN_KEY){
			move(4);
		}
		// on back pressed
		if(keyCode == Constants.RSK_KEY){
			warpClient.unsubscribeRoom(roomId);
			warpClient.leaveRoom(roomId);
			warpClient.disconnect();
			midlet.exitApp();
		}
		repaint();
	}
	
	public void keyRepeated(int keyCode){
		keyPressed(keyCode);
	}
	
	public void pointerPressed(int x, int y){
		if(x>(getWidth()*3)/4){
			move(1);
		}else if(x<getWidth()/4){
			move(2);
		}else if(x>getWidth()/4 && x<(getWidth()*3)/4 && y<getHeight()/2){
			move(3);
		}else if(x>getWidth()/4 && x<(getWidth()*3)/4 && y>getHeight()/2){
			move(4);
		}
		repaint();
	}
	
	public void pointerDragged(int x, int y){
		if(x>(getWidth()*3)/4){
			move(1);
		}else if(x<getWidth()/4){
			move(2);
		}else if(x>getWidth()/4 && x<(getWidth()*3)/4 && y<getHeight()/2){
			move(3);
		}else if(x>getWidth()/4 && x<(getWidth()*3)/4 && y>getHeight()/2){
			move(4);
		}
		repaint();
	}
	
	private void move(int dir){
		for(int i=0;i<player.size();i++){
			Player p = (Player)player.elementAt(i);
			if(p.getName().equals(monsterName)){
				p.move(dir);
				sendMoveEvent(dir);
			}
		}
	}
	
	// Response
	
		
	public void onRoomCreated(String roomId){
		
		if(roomId!=null && roomId.length()>0){
			warpClient.joinRoom(roomId);
		}
	}
	
	public void onRoomJoined(String roomId){
		if(roomId!=null && roomId.length()>0){
			GAME_START = true;
			warpClient.subscribeRoom(roomId);
			warpClient.getLiveRoomInfo(roomId);
			
		}else{// create new room
			
			warpClient.createRoom("GameRoom", "Saurav", 4, null);
		}
		repaint();
	}
	
	public void onLiveRoomInfo(String[] joinedUser){
		for(int i=0;i<joinedUser.length;i++){
			String name = joinedUser[i].trim();
			if(name.length()>0){
				String newname = name.substring(0, name.lastIndexOf('_'));
				int index = Integer.parseInt(name.substring(name.lastIndexOf('_')+1, name.length()));
				player.addElement(new Player(newname, index, getWidth()/2, getHeight()/2));
			}
		}
		repaint();
	}
	
	public void sendMoveEvent(int dir){
		try {
			JSONObject object = new JSONObject();
			object.put("dir", dir);
			warpClient.sendChat(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onMoveReceived(String playerName, int dir){
		for(int i=0;i<player.size();i++){
			Player p = (Player)player.elementAt(i);
			if(p.getName().endsWith(playerName)){
				p.move(dir);
			}
		}
		repaint();
	}
	
	public void onNewUserJoined(String name, int index){
		player.addElement(new Player(name, index, getWidth()/2, getHeight()/2));
	}
	
	public void onUserLeft(String name){
		for(int i=0;i<player.size();i++){
			Player p = (Player)player.elementAt(i);
			if(p.getName().equals(name)){
				player.removeElement(p);
			}
		}
	}

}
