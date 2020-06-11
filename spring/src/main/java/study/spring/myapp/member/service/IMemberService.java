package study.spring.myapp.member.service;

import study.spring.myapp.member.model.MemberVO;

public interface IMemberService {
	
	void insertMember(MemberVO member);
	MemberVO getMember(String userId);
	String getPassword(String userId);

}
