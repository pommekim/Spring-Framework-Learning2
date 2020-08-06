package study.spring.myapp.websocket.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import study.spring.myapp.websocket.model.ChattingRoom;

@Repository
public class ChatRepository implements IChatRepository {

	private Map<Integer, ChattingRoom> roomMap = new HashMap<>();

	@Override
	public List<ChattingRoom> loadAllRooms() {
		List<ChattingRoom> rooms = new ArrayList<>(roomMap.values());
		Collections.reverse(rooms);
		return rooms;
	}

	@Override
	public ChattingRoom selectRoom(int roomId) {
		return roomMap.get(roomId);
	}

	@Override
	public ChattingRoom createChattingRoom(String name) {
		ChattingRoom room = ChattingRoom.createRoom(name);
		roomMap.put(room.getRoomId(), room);
		return room;
	}


}
