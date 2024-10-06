
public class Bird extends Animal {

	private Boolean flyable;
	
	public Bird(String name, int gender, Boolean flyable) {
		super(name,gender);
		this.flyable = flyable;
	}
	public Boolean getFlyable() {
		return flyable;
	}
	public void fly() {
		if(flyable == true) {
			System.out.println(super.getName() + " is flying~");
		}
		else {
			System.out.println(super.getName() + " can’t fly QQ");
		}
	}
	public void breathe() {
		System.out.println("Birdie " + super.getName() + " is breathing");
	}
}
