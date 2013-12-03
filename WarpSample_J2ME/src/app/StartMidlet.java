package app;


import java.util.Random;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.shephertz.app42.appwarp.client.WarpClient;
import com.shephertz.app42.appwarp.event.ConnectEvent;
import com.shephertz.app42.appwarp.listener.ConnectionRequestListener;

public class StartMidlet extends MIDlet implements ConnectionRequestListener{

	
	private Display display;
	private MyCanvas mycanvas;
//	private String API_KEY = "<Your API Key>";
//	private String SECRET_KEY = "<Your Secret Key>";
	private int userMonsterIndex;
	private String userName;
	
	public static String API_KEY = "7eb1bc9e55767a734744d833a3fcf7cc0608710b0d90bcf22b65984b05b3ae16";
    public static String SECRET_KEY = "f5485d157b11c7e08281b16c2d8c95c83d94dd78db7c2eae205073d5155100ec";
   
	private WarpClient warpClient;
	
	public StartMidlet() {
		// TODO Auto-generated constructor stub4
		display=Display.getDisplay(this);
		initAppwarp();
		mycanvas = new MyCanvas(this, warpClient);
		

	}
	protected void startApp() throws MIDletStateChangeException {
		/*
		 * adding listener before connecting to appwarp server
		 */
		warpClient.addConnectionRequestListener(this);
		warpClient.addZoneRequestListener(new ZoneListener(mycanvas));
		warpClient.addRoomRequestListener(new RoomListener(mycanvas));
		warpClient.addNotificationListener(new NotificationListener(mycanvas));
		userName = Constants.getRandomUserName();
		userMonsterIndex = new Random().nextInt(4);
		warpClient.connectWithUserName(userName+"_"+userMonsterIndex);
		
	}
	protected void destroyApp(boolean b) throws MIDletStateChangeException {
		this.notifyDestroyed();
		
	}

	protected void pauseApp() {
		
		
	}
	
	public void exitApp(){
		try {
			destroyApp(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void onConnectDone(ConnectEvent event) {
		if(event.getResult()==0){// success case
			display.setCurrent(mycanvas);
			mycanvas.startGame(userName, userMonsterIndex);
			
		}else{// failure case
			System.out.println("Connection Failed: error code "+event.getResult());
		}
		
	}
	public void onDisconnectDone(ConnectEvent event) {
		
		
	}
	private void initAppwarp(){
		try {
			WarpClient.initialize(API_KEY, SECRET_KEY);
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
