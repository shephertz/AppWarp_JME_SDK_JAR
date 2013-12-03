package app;

import com.shephertz.app42.appwarp.event.AllRoomsEvent;
import com.shephertz.app42.appwarp.event.AllUsersEvent;
import com.shephertz.app42.appwarp.event.LiveUserInfoEvent;
import com.shephertz.app42.appwarp.event.MatchedRoomsEvent;
import com.shephertz.app42.appwarp.event.RoomEvent;
import com.shephertz.app42.appwarp.listener.ZoneRequestListener;

public class ZoneListener implements ZoneRequestListener{

	private MyCanvas mycanvas;
	
	public ZoneListener(MyCanvas mycanvas) {
		this.mycanvas = mycanvas;
	}
	
	public void onCreateRoomDone(RoomEvent event) {
		if(event.getResult()==0){// success case
			mycanvas.onRoomCreated(event.getData().getId());
		}
		
	}

	public void onDeleteRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onGetAllRoomsDone(AllRoomsEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
