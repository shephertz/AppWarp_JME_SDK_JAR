package app;

import java.util.Hashtable;

import com.shephertz.app42.appwarp.event.ChatEvent;
import com.shephertz.app42.appwarp.event.LobbyData;
import com.shephertz.app42.appwarp.event.RoomData;
import com.shephertz.app42.appwarp.event.UpdateEvent;
import com.shephertz.app42.appwarp.listener.NotifyListener;
import com.shephertz.app42.json.JSONObject;

public class NotificationListener implements NotifyListener{

	
	private MyCanvas mycanvas;
	
	public NotificationListener(MyCanvas mycanvas) {
		this.mycanvas = mycanvas;
	}
	
	public void onChatReceived(ChatEvent event) {
		String user = event.getSender();
		String message = event.getMessage();
		user = user.substring(0, user.lastIndexOf('_'));
		if(user.equals(mycanvas.monsterName)){
			return;
		}
		try{
			JSONObject jsonObject = new JSONObject(message);
			int dir = Integer.parseInt(jsonObject.get("dir").toString());
			mycanvas.onMoveReceived(user, dir);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void onRoomCreated(RoomData arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onUpdatePeersReceived(UpdateEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onUserChangeRoomProperty(RoomData arg0, String arg1,
			Hashtable arg2) {
		// TODO Auto-generated method stub
		
	}

	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onUserJoinedRoom(RoomData roomData, String userName) {
		System.out.println("on new user joined ....");
		String name = userName;
		String newname = name.substring(0, name.lastIndexOf('_'));
		int index = Integer.parseInt(name.substring(name.lastIndexOf('_')+1, name.length()));
		mycanvas.onNewUserJoined(newname, index);
		
	}

	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onUserLeftRoom(RoomData arg0, String username) {
		String name = username;
		String newname = name.substring(0, name.lastIndexOf('_'));
		mycanvas.onUserLeft(newname);
		
	}

	public void onPrivateChatReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onUserChangeRoomProperty(RoomData arg0, String arg1,
			Hashtable arg2, Hashtable arg3) {
		// TODO Auto-generated method stub
		
	}

}
