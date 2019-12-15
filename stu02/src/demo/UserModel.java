package demo;

public class UserModel extends MongoModel{
	
	private static String collname="userinfo";
	
	public UserModel() {
		super(collname);
	}
	
}
