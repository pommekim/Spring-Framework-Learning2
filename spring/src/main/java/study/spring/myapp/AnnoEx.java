package study.spring.myapp;

public class AnnoEx {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		MyContext context = new MyContext();
		MyData data = context.getInstance(MyData.class);
		System.out.println(data.name);
		System.out.println(data.toSay);
	}

}
