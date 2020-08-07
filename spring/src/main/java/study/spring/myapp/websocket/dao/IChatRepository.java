package study.spring.myapp.websocket.dao;

import java.util.List;
import study.spring.myapp.websocket.model.ChattingRoom;

public interface IChatRepository {
	
	List<ChattingRoom> loadAllRooms();
	ChattingRoom selectRoom(int roomId);
	ChattingRoom createChattingRoom(String name);
	

}
