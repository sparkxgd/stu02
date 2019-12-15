package demo;

public class StudentModel extends MongoModel{
	
	private static String collname="studentinfo";
	
	public StudentModel() {
		super(collname);
	}
	
}
