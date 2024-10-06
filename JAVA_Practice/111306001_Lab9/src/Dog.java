
public class Dog extends Animal{
	
	private String breed;
	
	public Dog(String name , int gender , String breed) {
		super(name,gender);
		this.breed = breed;
		
	}
	public String getBreed() {
		return breed;
	}
	public void bark() {
		System.out.println(super.getName() + " barks!");
	}
	public void run() {
		System.out.println(super.getName() + " is running!");
	}
	

}
