package study.spring.myapp.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.myapp.hr.model.EmpVO;
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
	public void insert() {}
	
	@PostMapping("/insert")
	public String insert(Model model, MemberVO member, RedirectAttributes redirectAttributes) {
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.insertMember(member);
		redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
		return "redirect:/login";
	}
	
	@RequestMapping("/view")
	public void getMember(String userId, Model model) {
		MemberVO member = memberService.getMember(userId);
		model.addAttribute("member", member);
	}

}
