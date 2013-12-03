package app;

import com.shephertz.app42.appwarp.event.LiveRoomInfoEvent;
import com.shephertz.app42.appwarp.event.RoomEvent;
import com.shephertz.app42.appwarp.listener.RoomRequestListener;

public class RoomListener implements RoomRequestListener{

	
	private MyCanvas mycanvas;
	
	public RoomListener(MyCanvas mycanvas) {
		this.mycanvas = mycanvas;
	}
	
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent event) {
		if(event.getResult()==0){// success case
			mycanvas.onLiveRoomInfo(event.getJoinedUsers());
		}
		
	}

	public void onJoinRoomDone(RoomEvent event) {
		if(event.getResult()==0){// success case
			mycanvas.onRoomJoined(event.getData().getId());
		}else{
			mycanvas.onRoomJoined(null);
		}
		
	}

	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

}
