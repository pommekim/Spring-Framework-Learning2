package study.spring.myapp.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		model.addAttribute("message", "insert");
		redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
		return "redirect:/login";
	}
	
	@RequestMapping("/view")
	public void getMember(String userId, Model model) {
		MemberVO member = memberService.getMember(userId);
		model.addAttribute("member", member);
	}
	
	@GetMapping("/update")
	public String update(String userId, Model model) {
		model.addAttribute("member", memberService.getMember(userId));
		model.addAttribute("message", "update");
		return "member/insert";
	}
	
	@PostMapping("/update")
	public String update(MemberVO member, Model model, RedirectAttributes redirectAttributes) {
		member.setPassword(bpe.encode(member.getPassword()));
		member.setEnabled(1);
		memberService.updateMember(member);
		redirectAttributes.addFlashAttribute("message", "회원 수정 완료");
		return "redirect:/member/view?userId="+member.getUserId();
	}
	
	@GetMapping("/delete")
	public String delete(String UserId, String password, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String dbpw = memberService.getPassword(authentication.getName());
		if(!bpe.matches(password, dbpw)) {
			model.addAttribute("message", "wrong");
			return "member/delete";
		}
		model.addAttribute("message", "right");
		return "member/delete";
	}
	
	@PostMapping("/delete")
	public String deleteMem(String userId, Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		memberService.deleteMember(authentication.getName());
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/list")
	public String getAllMembers(Model model) {
		List<MemberVO> memList = memberService.getMemberList();
		model.addAttribute("memList", memList);
		return "member/list";
	}
	
	

}
