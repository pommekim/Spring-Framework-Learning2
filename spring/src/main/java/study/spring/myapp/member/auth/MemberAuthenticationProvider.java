package study.spring.myapp.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import study.spring.myapp.member.model.MemberVO;
import study.spring.myapp.member.service.IMemberService;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication.getPrincipal() == null) return null;
		if(authentication.getCredentials() == null) return null;
		
		String userId = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		String dbpw = memberService.getPassword(userId);
		
		if(dbpw == null) return null;
		if(!bpe.matches(password, dbpw)) return null;
		
		MemberVO member = memberService.getMember(userId);
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, password, member.getAuthorities());
		result.setDetails(member);
		return result;
		
	}

	//supports가 true가 나오지 않으면 검증조차 하지 않음!
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
