package study.spring.myapp.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.spring.myapp.member.model.JSONVO;

@Controller
@RequestMapping("/json")
public class JsonController {
	
	//리턴타입이 String이어도 뷰리졸버를 안 찾음!!!
	@ResponseBody
	@RequestMapping(value="/ex", produces="application/json;charset=UTF-8")
	public String exam(@RequestBody JSONVO jsonVO) {
		return "name: " + jsonVO.getName() + ", age: " + jsonVO.getAge();
	}
	
	
	@RequestMapping(value="/example", produces="application/text;charset=UTF-8")
	@ResponseBody
	public String example(String userId) {
		return "ajax 데이터 : " + userId;
	}

}
