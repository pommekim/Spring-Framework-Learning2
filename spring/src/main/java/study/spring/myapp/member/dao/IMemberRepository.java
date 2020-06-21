package study.spring.myapp.member.dao;

import java.util.List;

import study.spring.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	void updateMember(MemberVO mem);
	void deleteMember(String userId);
	void deleteAuth(String userId);
	void updateFile(String userId);
	void deleteFile(String userId);
	List<MemberVO> getMemberList();

}
