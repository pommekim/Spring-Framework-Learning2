package study.spring.myapp.websocket.controller;

import org.springframework.ui.Model;

public interface IChatController {
	
	String getRoomList(Model model);
	String getRoom(int roomId, Model model);
	String createRoom(String roomName);
	int getRoomSize(int roomId);

}
