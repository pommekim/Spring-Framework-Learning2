package study.spring.myapp.member.service;

import java.util.List;

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
	public void insertAuth(String userId) {
		memberRepository.insertAuth(userId);
	}

	@Override
	public MemberVO getMember(String userId) {
		return memberRepository.getMember(userId);
	}

	@Override
	public String getPassword(String userId) {
		return memberRepository.getPassword(userId);
	}

	@Override
	public void updateMember(MemberVO mem) {
		memberRepository.updateMember(mem);
	}

	@Override
	public void deleteMember(String userId) {
		memberRepository.deleteMember(userId);
	}

	@Override
	public void deleteAuth(String userId) {
		memberRepository.deleteAuth(userId);
	}

	@Override
	public void updateFile(String userId) {
		memberRepository.updateFile(userId);
	}

	@Override
	public void deleteFile(String userId) {
		memberRepository.deleteFile(userId);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberRepository.getMemberList();
	}

	@Override
	public void updateAuth(MemberVO member) {
		memberRepository.updateAuth(member);
	}

	@Override
	public void updateEnabled(MemberVO member) {
		memberRepository.updateEnabled(member);
	}

	@Override
	public boolean checkId(String userId) {
		return memberRepository.checkId(userId);
	}

	

}
