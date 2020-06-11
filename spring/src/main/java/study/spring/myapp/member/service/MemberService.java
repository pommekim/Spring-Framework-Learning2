package study.spring.myapp.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study.spring.myapp.member.dao.IMemberRepository;
import study.spring.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	IMemberRepository memberRepository;

	@Override
	@Transactional("txManager")
	public void insertMember(MemberVO member) {
		memberRepository.insertMember(member);
		memberRepository.insertAuth(member.getUserId());
	}

	@Override
	public MemberVO getMember(String userId) {
		return memberRepository.getMember(userId);
	}

	@Override
	public String getPassword(String userId) {
		return memberRepository.getPassword(userId);
	}

}
