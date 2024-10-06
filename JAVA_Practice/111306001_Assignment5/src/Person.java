
public class Person {
	
	private int ID;
	private String name;
	
	public Person(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	public int getID(){
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getInfo() {
		String info = "Person[ID=" + ID + ", name=" + name + "]";
		return info;
	}
}
