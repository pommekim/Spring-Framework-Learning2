package study.spring.myapp;

public class MyData {
	
	@MyAnnotation
	String name;
	
	@MyAnnotation(name="어노테이션을 이용한 값")
	String toSay;

}
