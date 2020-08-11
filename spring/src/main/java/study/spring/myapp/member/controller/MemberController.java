package study.spring.myapp.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.myapp.member.model.MemberVO;
import study.spring.myapp.member.service.IMemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bpe;
	
	@GetMapping("/insert")
	public void insert(Model model) {
		model.addAttribute("message", "insert");
	}
	
	@PostMapping("/insert")
	public String insert(Model model, MemberVO member, RedirectAttributes redirectAttributes) {
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.insertMember(member);
		redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
		return "redirect:/login";
	}
	
	
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/{userId}")
	public String getMember(@PathVariable String userId, Model model) {
		model.addAttribute("member", memberService.getMember(userId));
		return "member/view";
	}
	
	
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/update/{userId}")
	public String update(Model model, @PathVariable String userId) {
		MemberVO mem = memberService.getMember(userId);
		model.addAttribute("member", mem);
		model.addAttribute("message", "update");
		return "member/insert";
	}
	
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping("/update")
	public String update(MemberVO mem, Authentication auth) {
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		} else {
			if(bpe.matches(mem.getPassword(), memberService.getPassword(mem.getUserId()))) {
				
			} else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}
		memberService.updateMember(mem);
		return "redirect:/member/"+mem.getUserId();
	}
	
	
	@PreAuthorize("isAuthenticated() and #userId==principal.username or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping("/delete")
	public String delete(Authentication auth, String userId, String password) {
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		}else {
			if(bpe.matches(password, memberService.getPassword(userId))) {
				
			} else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}
		memberService.deleteMember(userId);
		return "redirect:/member/list";
	}
	
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/list")
	public String getAllMembers(Model model) {
		List<MemberVO> memList = memberService.getMemberList();
		model.addAttribute("memList", memList);
		return "member/list";
	}
	
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MASTER')")
	@PostMapping("/auth")
	public String updateAuth(String userId, String auth) {
		MemberVO mem = memberService.getMember(userId);
		mem.setAuth(auth);
		memberService.updateAuth(mem);
		return "redirect:/member/"+userId;
	}
	
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@RequestMapping("/enabled")
	public String updateEnabled(String userId) {
		MemberVO mem = memberService.getMember(userId);
		if(mem.getEnabled() == 1) {
			mem.setEnabled(0);
		} else {
			mem.setEnabled(1);
		}
		memberService.updateEnabled(mem);
		return "redirect:/member/"+userId;
	}
	
	
	@RequestMapping(value="/check", produces="application/json;charset=UTF-8")
	@ResponseBody
	public boolean checkId(String userId) {
		return memberService.checkId(userId);
	}
	
	

}
