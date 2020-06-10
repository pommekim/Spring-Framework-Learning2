package study.spring.myapp.member.dao;

import study.spring.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);

}
